package br.com.jlm.minhastarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jlm.minhastarefas.repository.TarefaRepository;
import br.com.jlm.minhastarefas.model.Tarefa;

/**
 * Controlador REST responsável por expor os endpoints relacionados à entidade Tarefa.
 * 
 * Essa classe utiliza as anotações do Spring para mapear requisições HTTP e realizar operações
 * de CRUD (Create, Read, Update, Delete) na base de dados de tarefas.
 */


@RestController // Define que essa classe é um controlador REST e que os métodos retornarão dados diretamente
public class TarefaControler {
	
	@Autowired // Injeta automaticamente uma instância do repositório no controlador
	private TarefaRepository repositorio;

	/**
	 * Endpoint para listar todas as tarefas cadastradas.
	 * 
	 * @return uma lista de todas as tarefas no banco de dados
	 */
	@GetMapping("/tarefa") // Mapeia requisições GET para o endpoint /tarefa
	public List<Tarefa> todasTarefas() {
		return repositorio.findAll(); // Retorna todas as tarefas armazenadas
	}
	
	/**
	 * Busca uma tarefa específica com base no ID fornecido na URL.
	 * 
	 * @param id identificador da tarefa a ser buscada
	 * @return a tarefa correspondente ao ID, ou null se não for encontrada
	 */
	@GetMapping("/tarefa/{id}") // Mapeia requisições GET com um parâmetro de caminho
	public Tarefa buscarTarefa(@PathVariable Integer id) {
		// Busca uma tarefa pelo ID; se não encontrar, retorna null
		return repositorio.findById(id).orElse(null);
	}
	
	/**
	 * Salva uma nova tarefa no banco de dados.
	 * 
	 * @param tarefa objeto da tarefa enviado no corpo da requisição (JSON)
	 * @return a tarefa salva, com ID gerado automaticamente
	 */
	@PostMapping("/tarefa") // Mapeia requisições POST para o endpoint /tarefa
	public Tarefa salvarTarefa(@RequestBody Tarefa tarefa) {
		// O corpo da requisição (JSON) é convertido em um objeto Tarefa automaticamente
		return repositorio.save(tarefa);
	}
	
	/**
	 * Remove uma tarefa do banco de dados com base no ID fornecido.
	 * 
	 * @param id identificador da tarefa a ser excluída
	 */
	@DeleteMapping("/tarefa/{id}") // Mapeia requisições DELETE para o endpoint /tarefa/{id}
	public void excluirTarefa(@PathVariable Integer id) {
		repositorio.deleteById(id); // Exclui a tarefa pelo ID
	}
}
