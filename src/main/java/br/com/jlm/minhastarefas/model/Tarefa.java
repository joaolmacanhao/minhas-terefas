package br.com.jlm.minhastarefas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Representa uma tarefa atribuída a um usuário, com descrição, data de entrega,
 * status, visibilidade, categoria e vínculo com o usuário.
 * 
 * Esta classe é uma entidade JPA e será mapeada para a tabela "tarefas".
 */


@Entity // Indica que essa classe será uma entidade JPA, ou seja, representará uma tabela no banco de dados
@Table(name = "tarefas") // Define o nome da tabela no banco como "tarefas"
@NamedQuery (name = "Tarefa.tarefasPorCategoria" , query = "select t from Tarefa t inner join t.categoria c  where c.nome = ?1")
public class Tarefa {

	@Id // Indica o campo de chave primária da tabela
	@GeneratedValue(strategy = GenerationType.AUTO) // O ID será gerado automaticamente (de acordo com o banco)
	private Integer id;

	@NotBlank(message = "{tarefa.descricao.not-blank}")//Uso do BeanValidation para nao permitir um campo vazio
	@Size(min = 5, max = 150 , message = "{tarefa.descricao.size}")
	@Column(name = "descricaoTarefa", nullable = false, length = 150) // Define o nome da coluna, torna obrigatória e limita o tamanho																
	private String descricao;

	@FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
	private LocalDate dataEntrega; // Será mapeado como uma coluna do tipo DATE no banco de dados

	@Enumerated(EnumType.STRING) // Armazena o enum como String ao invés de ordinal (número)
	@Column(nullable = false, length = 100)
	private TarefaStatus status;

	private boolean visível; // Campo booleano que pode ser usado para "arquivar" ou esconder tarefas

	@ManyToOne // Muitas tarefas podem pertencer a uma única categoria
	@JoinColumn(nullable = false) // Cria uma coluna de chave estrangeira (categoria_id) e a torna obrigatória
	private TarefaCategoria categoria;

	@ManyToOne // Muitas tarefas podem pertencer a um único usuário
	@JoinColumn(nullable = false) // Cria uma coluna de chave estrangeira (usuario_id) e a torna obrigatória
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public TarefaStatus getStatus() {
		return status;
	}

	public void setStatus(TarefaStatus status) {
		this.status = status;
	}

	public boolean isVisível() {
		return visível;
	}

	public void setVisível(boolean visível) {
		this.visível = visível;
	}

	public TarefaCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(TarefaCategoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
