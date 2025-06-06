package br.com.jlm.minhastarefas.repository;


import java.util.List;

/**
 * Repositório responsável por operações de acesso a dados da entidade Tarefa.
 * 
 * Esta interface herda métodos prontos do Spring Data JPA, como salvar, deletar,
 * buscar por ID, entre outros.
 * 
 * O tipo da entidade é Tarefa e o tipo da chave primária é Integer.
 * 
 * Exemplo de uso:
 * 
 * tarefaRepository.findById(1);
 * tarefaRepository.save(tarefa);
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jlm.minhastarefas.model.Tarefa;
import br.com.jlm.minhastarefas.model.TarefaCategoria;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
	
	/**
	 * Busca uma lista de tarefas que tenham exatamente a descrição informada.
	 * 
	 * @param descricao descrição exata a ser buscada
	 * @return lista de tarefas com a descrição fornecida
	 */
	public List<Tarefa> findByDescricao(String descricao);
	
	/**
	 * Busca uma lista de tarefas que contenham a descrição de forma parcial,
	 * utilizando o operador LIKE do SQL.
	 * 
	 * Exemplo:
	 * - findByDescricaoLike("%Spring%") busca todas as tarefas cuja descrição contenha "Spring"
	 * 
	 * @param descricao padrão de busca usando LIKE (ex: "%texto%")
	 * @return lista de tarefas que contenham o texto informado na descrição
	 */
	public List<Tarefa> findByDescricaoLike(String descricao);
	
	public List<Tarefa> findByCategoria (TarefaCategoria categoria);
	
	@Query("select t from Tarefa t inner join t.categoria c  where c.nome = ?1")
	public List<Tarefa> findByNomeCategoria(String nomeCategoria);
	
	public List<Tarefa> tarefasPorCategoria (String nomeCategoria);

}