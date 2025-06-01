package br.com.jlm.minhastarefas.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Representa um usuário do sistema.
 * 
 * Esta entidade é mapeada para a tabela "usuarios" no banco de dados.
 * Cada usuário pode ter várias tarefas associadas e múltiplos papéis (roles).
 */

@Entity // Entidade que representa a tabela de usuários
@Table(name = "usuarios") // Define o nome da tabela no banco
public class Usuario {

	private String nome; // Nome do usuário
	
	private String senha; // Senha (deverá ser criptografada na aplicação real)

	@Id // Chave primária
	@GeneratedValue(strategy = GenerationType.AUTO) // ID gerado automaticamente
	private Integer id;
	
	@OneToMany(mappedBy = "usuario" , fetch = FetchType.LAZY) 
	// Um usuário pode ter várias tarefas. "mappedBy" indica que o campo "usuario" em Tarefa é o lado dominante
	private List<Tarefa> tarefas;
	
	@ManyToMany (fetch = FetchType.LAZY) // Muitos usuários podem ter muitos papéis (roles), e vice-versa
	@JoinTable(	name = "usuariosRoles", // Nome da tabela de junção
				joinColumns = @JoinColumn(name = "usuarioId"), // FK para a entidade Usuario
				inverseJoinColumns = @JoinColumn (name = "roleId")) // FK para a entidade Role
	private Set<Role> roles = new HashSet<>(); // Conjunto de permissões/roles do usuário

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
}
