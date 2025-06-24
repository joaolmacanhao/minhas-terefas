package br.com.jlm.minhastarefas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jlm.minhastarefas.services.TarefaService;
import br.com.jlm.minhastarefas.controller.request.TarefaRequest;
import br.com.jlm.minhastarefas.controller.responde.TarefaResponse;
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
	private TarefaService service;
	
	@Autowired
	private ModelMapper mapper;

	/**
	 * Endpoint para listar todas as tarefas cadastradas.
	 * 
	 * @return uma lista de todas as tarefas no banco de dados
	 */
	@GetMapping("/tarefa") // Mapeia requisições GET para o endpoint /tarefa
	public List<TarefaResponse> todasTarefas(@RequestParam Map<String, String> parametros) {
		
		// Verifica se não foram passados parâmetros na requisição
		List<Tarefa> tarefas = new ArrayList<>();
		
		if (parametros.isEmpty()) {
			// Se nenhum parâmetro foi informado, retorna todas as tarefas do banco
			tarefas =  service.getTodasTarefas(); // Retorna todas as tarefas armazenadas
		}else {
			
			// Se houver parâmetros, tenta obter o valor associado à chave "descricao"
			String descricao = parametros.get("descricao");
			tarefas = service.getTarefasPorDescricao(descricao);
			
		}
		
		List<TarefaResponse> tarefasResponse = tarefas
				.stream().map(tarefa -> mapper
				.map(tarefa, TarefaResponse.class))
				.collect(Collectors.toList());

		return tarefasResponse;
	}
	
	/**
	 * Busca uma tarefa específica com base no ID fornecido na URL.
	 * 
	 * @param id identificador da tarefa a ser buscada
	 * @return a tarefa correspondente ao ID, ou null se não for encontrada
	 */
	@GetMapping("/tarefa/{id}") // Mapeia requisições GET com um parâmetro de caminho
	public TarefaResponse buscarTarefa(@PathVariable Integer id) {
		// Busca uma tarefa pelo ID; se não encontrar, retorna null
		Tarefa tarefa = service.getTarefaById(id);
		
		TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);
		
		return tarefaResponse;
	}
	
	/**
	 * Salva uma nova tarefa no banco de dados.
	 * 
	 * @param tarefa objeto da tarefa enviado no corpo da requisição (JSON)
	 * @return a tarefa salva, com ID gerado automaticamente
	 */
	@PostMapping("/tarefa") // Mapeia requisições POST para o endpoint /tarefa
	public TarefaResponse salvarTarefa(@Valid @RequestBody TarefaRequest tarefaReq) { //@Valid para validar o bean usado para nao aceitar campo vazio
		Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class );
		return mapper.map(service.salvarTarefa(tarefa), TarefaResponse.class);
	}
	
	/**
	 * Remove uma tarefa do banco de dados com base no ID fornecido.
	 * 
	 * @param id identificador da tarefa a ser excluída
	 */
	@DeleteMapping("/tarefa/{id}") // Mapeia requisições DELETE para o endpoint /tarefa/{id}
	public void excluirTarefa(@PathVariable Integer id) {
		service.deleteById(id); // Exclui a tarefa pelo ID
	}
}