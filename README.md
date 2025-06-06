Minhas Tarefas – Backend
O projeto Minhas Tarefas é uma aplicação backend desenvolvida para gerenciar tarefas de forma simples e eficiente. Ele oferece funcionalidades para criação, listagem, atualização e remoção de tarefas, permitindo que os usuários organizem suas atividades diárias.

Este projeto foi desenvolvido com o intuito de estudo do framework Spring Boot, portanto, contém diversos comentários explicativos no código para auxiliar no aprendizado.

🚀 Tecnologias Utilizadas
Java (versão 17 ou superior)

Spring Boot – Framework para desenvolvimento de aplicações Java

JPA/Hibernate – Mapeamento objeto-relacional

H2 Database – Banco de dados em memória para desenvolvimento

PostgreSQL – Banco de dados relacional utilizado em ambiente de produção

JUnit – Framework para testes unitários

Maven – Gerenciador de dependências e build

🛠️ Funcionalidades
Criação de Tarefas: Permite adicionar novas tarefas com título, descrição e data de vencimento.

Listagem de Tarefas: Exibe todas as tarefas cadastradas.

Atualização de Tarefas: Possibilita editar os detalhes de uma tarefa existente.

Remoção de Tarefas: Remove tarefas que não são mais necessárias.

📦 Como Executar o Projeto
1. Clonar o Repositório
bash
Copiar
Editar
git clone https://github.com/joaolmacanhao/minhas-terefas.git
cd minhas-terefas
2. Compilar e Executar
Utilize o Maven para compilar e executar a aplicação:

mvn clean install
mvn spring-boot:run
A aplicação estará disponível em http://localhost:8080.

🧪 Testes
Os testes automatizados estão localizados no diretório src/test/java. Para executá-los, utilize o comando Maven:

mvn test
🗄️ Configuração de Banco de Dados
Para facilitar o desenvolvimento e os testes, o projeto utiliza o banco de dados H2 em memória. No entanto, em ambientes de produção, é recomendado utilizar um banco de dados relacional como o PostgreSQL.

📁 Arquivos de Configuração
src/main/resources/application.properties: Configurações padrão do banco de dados (H2).

src/main/resources/application-prod.properties: Configurações específicas para o ambiente de produção (PostgreSQL).

🔧 Configuração do PostgreSQL
Para utilizar o PostgreSQL em ambiente de produção, adicione as seguintes configurações no arquivo application-prod.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/minhas_terefas
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
Substitua seu_usuario e sua_senha pelas credenciais do seu banco de dados PostgreSQL.


📌 Observações
Este projeto utiliza o banco de dados H2 em memória para desenvolvimento.

Para produção, recomenda-se configurar um banco de dados persistente adequado, como o PostgreSQL.

A autenticação e autorização de usuários podem ser implementadas conforme necessário.

O código contém diversos comentários explicativos para auxiliar no aprendizado do Spring Boot.

