package br.com.jlm.minhastarefas.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jlm.minhastarefas.exception.TarefaStatusException;
import br.com.jlm.minhastarefas.model.Tarefa;
import br.com.jlm.minhastarefas.model.TarefaStatus;

@SpringBootTest
public class TarefaServiceIntegrationTest {
	
	@Autowired
	private TarefaService service;
	
	@Test
	void deveIniciarTarefa() {
		Tarefa tarefa = service.inciarTarefaPorId(3);
		Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getStatus());
	}
	
	@Test
	void naoDeveIniciarTarefaConcluida() {
		Tarefa tarefa = service.getTarefaById(3);
		
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		service.salvarTarefa(tarefa); 
		
		
		//tarefa = service.inciarTarefaPorId(3);
		
		Assertions.assertThrows(TarefaStatusException.class, () -> service.inciarTarefaPorId(3)); 
	}

}


