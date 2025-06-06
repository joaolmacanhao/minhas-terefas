package br.com.jlm.minhastarefas.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jlm.minhastarefas.model.Tarefa;

@SpringBootTest
public class TarefaRepositoryTest {

	@Autowired
	private TarefaRepository repositorio;
	
	@Test
	void testFindByNomeRepository() {
		
		List<Tarefa> tarefas = repositorio.findByNomeCategoria("Estudos");
		Assertions.assertEquals(2, tarefas.size());
	}
	
	@Test
	void testTarefasPorCategoria() {
		
		List<Tarefa> tarefas = repositorio.tarefasPorCategoria("Estudos");
		Assertions.assertEquals(2, tarefas.size());
	}
}
