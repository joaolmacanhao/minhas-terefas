package br.com.jlm.minhastarefas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa um papel (role) que pode ser atribuído a um usuário.
 * 
 * Esta entidade é mapeada para a tabela "roles" no banco de dados.
 * Utilizada no controle de permissões e perfis de acesso.
 */

@Entity // Define que a classe representa uma tabela no banco
@Table(name = "roles") // Nome da tabela será "roles"
public class Role {

	@Id // Chave primária
	@GeneratedValue(strategy = GenerationType.AUTO) // Geração automática de ID
	private Integer id;
	
	@Enumerated(EnumType.STRING) // Salva o enum como String
	@Column(length = 20) // Limita o tamanho da string no banco
	private ERole name; // Nome do papel (definido pelo enum ERole)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	
}
