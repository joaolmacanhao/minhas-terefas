package br.com.jlm.minhastarefas.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jlm.minhastarefas.exception.TarefaStatusException;
import br.com.jlm.minhastarefas.model.Tarefa;
import br.com.jlm.minhastarefas.model.TarefaStatus;

/**
 * Classe de teste de integração para a camada de serviço.
 * Verifica as regras de negócio relacionadas às mudanças de status da tarefa.
 */
@SpringBootTest // Indica que os testes devem rodar em um contexto completo do Spring Boot
public class TarefaServiceIntegrationTest {

	@Autowired
	private TarefaService service; // Injeta a dependência do serviço de tarefas

	@Test
	void deveIniciarTarefa() {
		// Testa o fluxo de início de uma tarefa válida
		Tarefa tarefa = service.inciarTarefaPorId(3);
		
		// Verifica se o status foi alterado corretamente para EM_ANDAMENTO
		Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getStatus());
	}

	@Test
	void naoDeveIniciarTarefaConcluida() {
		// Busca uma tarefa e simula que ela está CONCLUÍDA
		Tarefa tarefa = service.getTarefaById(3);
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		service.salvarTarefa(tarefa);

		// Garante que iniciar uma tarefa CONCLUÍDA lança exceção
		Assertions.assertThrows(TarefaStatusException.class, () -> service.inciarTarefaPorId(3));
	}

	@Test
	void naoDeveConcluirTarefaCancelada() {
		// Simula uma tarefa que está CANCELADA
		Tarefa tarefa = service.getTarefaById(3);
		tarefa.setStatus(TarefaStatus.CANCELADA);
		service.salvarTarefa(tarefa);

		// Verifica se é impedido de iniciar uma tarefa CANCELADA
		Assertions.assertThrows(TarefaStatusException.class, () -> service.inciarTarefaPorId(3));
	}

	@Test
	void naoDeveCancelarTarefaConcluida() {
		// Simula uma tarefa já CONCLUÍDA
		Tarefa tarefa = service.getTarefaById(6);
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		service.salvarTarefa(tarefa);

		// Verifica se tentar cancelar uma tarefa CONCLUÍDA lança exceção
		Assertions.assertThrows(TarefaStatusException.class, () -> service.cancelarTarefaPorId(6));
	}

	@Test
	void naoDeveIniciarTarefaEmAndamento() {
		// Simula uma tarefa já EM_ANDAMENTO
		Tarefa tarefa = service.getTarefaById(9);
		tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);
		service.salvarTarefa(tarefa);

		// Garante que iniciar novamente uma tarefa EM_ANDAMENTO não é permitido
		Assertions.assertThrows(TarefaStatusException.class, () -> service.inciarTarefaPorId(9));
	}
}
