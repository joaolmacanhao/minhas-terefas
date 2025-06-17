package br.com.jlm.minhastarefas.config;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.jlm.minhastarefas.model.Tarefa;
import br.com.jlm.minhastarefas.model.TarefaCategoria;
import br.com.jlm.minhastarefas.model.TarefaStatus;
import br.com.jlm.minhastarefas.model.Usuario;
import br.com.jlm.minhastarefas.repository.TarefaCategoriaRepository;
import br.com.jlm.minhastarefas.repository.TarefaRepository;
import br.com.jlm.minhastarefas.repository.UsuarioRepository;

/**
 * Classe de configuração responsável por carregar dados iniciais no banco
 * de dados quando o perfil "dev" estiver ativo.
 * 
 * <p>Cria um usuário, uma categoria de tarefa e uma tarefa associada aos dois.
 * Utiliza {@link CommandLineRunner} para executar o código logo após a inicialização
 * da aplicação.</p>
 */

@Configuration
@Profile("dev") // Esta configuração só será executada se o perfil "dev" estiver ativo
public class CarregarBaseDeDados {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaCategoriaRepository tarefaCategoriaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    /**
     * Método que será executado automaticamente ao iniciar a aplicação no perfil "dev".
     * 
     * @return CommandLineRunner que insere dados de exemplo no banco de dados.
     */
    
    @Bean //Chama esse método durante a inicialização e use o valor retornado como um objeto gerenciado pelo Spring.
    CommandLineRunner executar() {
        return args -> {
            // Criando um novo usuário de exemplo
            Usuario usuario = new Usuario();
            usuario.setNome("Admin");
            usuario.setSenha("123456");
            usuarioRepository.save(usuario);

            // Criando uma nova categoria de tarefa
            TarefaCategoria categoria = new TarefaCategoria();
            categoria.setNome("Estudos");
            tarefaCategoriaRepository.save(categoria);

            // Criando uma nova tarefa associada ao usuário e à categoria criados
            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao("Estudar Spring");
            tarefa.setDataEntrega(LocalDate.now().plusDays(4)); // Data de entrega para amanhã
            tarefa.setStatus(TarefaStatus.ABERTO); // Status inicial como ABERTO
            tarefa.setVisível(true); // Tarefa visível
            tarefa.setCategoria(categoria); // Associação com a categoria "Estudos"
            tarefa.setUsuario(usuario); // Associação com o usuário "Admin"
            tarefaRepository.save(tarefa);
            
            
            Usuario usuario2 = new Usuario();
            usuario2.setNome("Admin2");
            usuario2.setSenha("1234562");
            usuarioRepository.save(usuario2);

            // Criando uma nova categoria de tarefa
            TarefaCategoria categoria2 = new TarefaCategoria();
            categoria2.setNome("Estudos");
            tarefaCategoriaRepository.save(categoria2);

            // Criando uma nova tarefa associada ao usuário e à categoria criados
            Tarefa tarefa2 = new Tarefa();
            tarefa2.setDescricao("Estudar Spring 2222");
            tarefa2.setDataEntrega(LocalDate.now().plusDays(2)); // Data de entrega para amanhã
            tarefa2.setStatus(TarefaStatus.ABERTO); // Status inicial como ABERTO
            tarefa2.setVisível(true); // Tarefa visível
            tarefa2.setCategoria(categoria2); // Associação com a categoria "Estudos"
            tarefa2.setUsuario(usuario2); // Associação com o usuário "Admin"
            tarefaRepository.save(tarefa2);
            
            Usuario usuario3 = new Usuario();
            usuario3.setNome("Admin2");
            usuario3.setSenha("1234562");
            usuarioRepository.save(usuario3);

            // Criando uma nova categoria de tarefa
            TarefaCategoria categoria3 = new TarefaCategoria();
            categoria3.setNome("Estudos");
            tarefaCategoriaRepository.save(categoria3);

            // Criando uma nova tarefa associada ao usuário e à categoria criados
            Tarefa tarefa3 = new Tarefa();
            tarefa3.setDescricao("Estudar Spring 2222");
            tarefa3.setDataEntrega(LocalDate.now().plusDays(2)); // Data de entrega para amanhã
            tarefa3.setStatus(TarefaStatus.ABERTO); // Status inicial como ABERTO
            tarefa3.setVisível(true); // Tarefa visível
            tarefa3.setCategoria(categoria3); // Associação com a categoria "Estudos"
            tarefa3.setUsuario(usuario3); // Associação com o usuário "Admin"
            tarefaRepository.save(tarefa3);
        };
    }
}
