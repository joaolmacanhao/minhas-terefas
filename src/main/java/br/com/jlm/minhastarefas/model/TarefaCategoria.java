package br.com.jlm.minhastarefas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * Representa uma categoria atribuída a uma tarefa.
 * 
 * Esta classe é uma entidade JPA e será mapeada para a tabela "tarefasUsuarios".
 * Cada categoria pode ser associada a várias tarefas.
 */

@Entity // Define que esta classe é uma entidade JPA
@Table (name = "tarefasUsuarios") // Define o nome da tabela no banco (poderia ser "categorias")
public class TarefaCategoria {

	@NotBlank(message = "O campo nome não pode estar vazio")
	@Size(max = 50 , message = "O Campo deve ter no máximo 50 caracteres")
	@Column (length = 50)
	private String nome; // Nome da categoria da tarefa
	
	@Id // Chave primária
	@GeneratedValue(strategy = GenerationType.AUTO) // Geração automática do ID
	private Integer id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
