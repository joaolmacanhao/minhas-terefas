package br.com.jlm.minhastarefas.repository;

/**
 * Repositório responsável pelas operações de banco de dados da entidade Usuario.
 * 
 * Utiliza a infraestrutura do Spring Data JPA para realizar operações CRUD.
 */

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jlm.minhastarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
