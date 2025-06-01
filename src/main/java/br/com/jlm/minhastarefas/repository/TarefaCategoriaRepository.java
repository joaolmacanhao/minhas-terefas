package br.com.jlm.minhastarefas.repository;

/**
 * Repositório para gerenciar operações de persistência da entidade TarefaCategoria.
 * 
 * Interface que herda funcionalidades básicas do Spring Data JPA, como inserção,
 * atualização, exclusão e busca.
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jlm.minhastarefas.model.TarefaCategoria;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Integer>{

}