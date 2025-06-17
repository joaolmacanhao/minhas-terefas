package br.com.jlm.minhastarefas.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jlm.minhastarefas.exception.TarefaStatusException;
import br.com.jlm.minhastarefas.model.Tarefa;
import br.com.jlm.minhastarefas.model.TarefaStatus;
import br.com.jlm.minhastarefas.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository repositorio;

	public List<Tarefa> getTodasTarefas() {

		return repositorio.findAll();
	}

	public List<Tarefa> getTarefasPorDescricao(String descricao) {

		return repositorio.findByDescricao("%" + descricao + "%");
	}
	
	public Tarefa getTarefaById (Integer id) {
		
		return repositorio.findById(id).orElseThrow(()-> new EntityNotFoundException());
	}
	
	public Tarefa salvarTarefa (Tarefa tarefa) {
		return repositorio.save(tarefa);
		
	}
	
	public void deleteById (Integer id) {
		
		repositorio.deleteById(id);
	}
	
	public Tarefa inciarTarefaPorId(Integer id) {
		Tarefa tarefa = getTarefaById(id);
		
		if(!TarefaStatus.ABERTO.equals(tarefa.getStatus())) throw new TarefaStatusException();
		
		tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);
		
		repositorio.save(tarefa);
		
		return tarefa;
	}
}
