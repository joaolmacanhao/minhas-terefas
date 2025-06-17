package br.com.jlm.minhastarefas.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.jlm.minhastarefas.exception.TarefaStatusException;
import br.com.jlm.minhastarefas.model.Tarefa;
import br.com.jlm.minhastarefas.model.TarefaStatus;
import br.com.jlm.minhastarefas.repository.TarefaRepository;

/**
 * Teste unitário da classe TarefaService usando Mockito.
 * Aqui testamos apenas a lógica de negócio, simulando as interações com o repositório.
 */
@ExtendWith(MockitoExtension.class) // Extensão do JUnit para suporte ao Mockito
public class TarefaServiceTest {

	@Mock // Simula o comportamento do TarefaRepository (sem acessar banco real)
	private TarefaRepository repositorio;

	@InjectMocks // Injeta o mock do repositório dentro da instância real de TarefaService
	private TarefaService service;

	@Test
	void naoDeveConcluirTarefaCancelada() {
		// Cria uma tarefa com status CANCELADA
		Tarefa tarefa = new Tarefa();
		tarefa.setId(1);
		tarefa.setDescricao("qualquer coisa");
		tarefa.setStatus(TarefaStatus.CANCELADA);

		// Configura o mock para retornar a tarefa quando buscar por ID
		Mockito.when(repositorio.findById(1)).thenReturn(Optional.of(tarefa));

		// Verifica se a tentativa de concluir uma tarefa CANCELADA lança exceção
		Assertions.assertThrows(TarefaStatusException.class, () -> service.concluirTarefaPorId(1));
	}

	@Test
	void naoDeveCancelarTarefaConcluida() {
		// Tarefa já CONCLUÍDA
		Tarefa tarefa = new Tarefa();
		tarefa.setId(1);
		tarefa.setDescricao("qualquer coisa");
		tarefa.setStatus(TarefaStatus.CONCLUIDA);

		Mockito.when(repositorio.findById(1)).thenReturn(Optional.of(tarefa));

		// Espera uma exceção ao tentar cancelar uma tarefa já concluída
		Assertions.assertThrows(TarefaStatusException.class, () -> service.cancelarTarefaPorId(1));
	}

	@Test
	void naoDeveIniciarTarefaConcluida() {
		// Tarefa CONCLUÍDA
		Tarefa tarefa = new Tarefa();
		tarefa.setId(1);
		tarefa.setDescricao("qualquer coisa");
		tarefa.setStatus(TarefaStatus.CONCLUIDA);

		Mockito.when(repositorio.findById(1)).thenReturn(Optional.of(tarefa));

		// Não deve iniciar tarefa CONCLUÍDA
		Assertions.assertThrows(TarefaStatusException.class, () -> service.inciarTarefaPorId(1));
	}

	@Test
	void naoDeveIniciarTarefaEmAndamento() {
		// Tarefa já EM_ANDAMENTO
		Tarefa tarefa = new Tarefa();
		tarefa.setId(1);
		tarefa.setDescricao("qualquer coisa");
		tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);

		Mockito.when(repositorio.findById(1)).thenReturn(Optional.of(tarefa));

		// Tentar iniciar novamente deve lançar exceção
		Assertions.assertThrows(TarefaStatusException.class, () -> service.inciarTarefaPorId(1));
	}
}
