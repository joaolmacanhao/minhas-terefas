package br.com.jlm.minhastarefas.repository;


/**
 * Repositório responsável por operações de acesso a dados da entidade Tarefa.
 * 
 * Esta interface herda métodos prontos do Spring Data JPA, como salvar, deletar,
 * buscar por ID, entre outros.
 * 
 * O tipo da entidade é {@code Tarefa} e o tipo da chave primária é {@code Integer}.
 * 
 * Exemplo de uso:
 * 
 * tarefaRepository.findById(1);
 * tarefaRepository.save(tarefa);
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jlm.minhastarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}
