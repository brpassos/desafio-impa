# Gerenciamento de Tarefas - API Restful

Este projeto foi desenvolvido para realizar a tarefa de desenvolver uma API Restful para resolver a história abaixo:

**"Como um usuário, eu quero ter a possibilidade de listar, cadastrar, exibir, atualizar, deletar e concluir minhas tarefas."**

### Desenvolvimento

O projeto foi implementado em Java 11 com o framework Spring e foi utilizado o Banco de Dados MySQL. Para o ambiente de desenvolvimento foi utilizada IDE IntelliJ com um projeto criado no [spring initializr](https://start.spring.io/) e o Mysql em um container docker com docker-compose com a configuração abaixo:

~~~
version: "2"

services:
  db:
    image: mysql
    container_name: mysql-desafio-impa
    volumes:
      - ~/dir-docker/desafio-impa/data:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_DATABASE: desafio_impa
    ports:
      - 3306:3306
~~~

### Documentação

Para documentar a API foi utilizado o [OpenAPI](https://www.openapis.org/) (Swagger 3) com a dependência abaixo:

~~~
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.4</version>
</dependency>
~~~

### Ambiente de Testes (Heroku)

Com o objeto de facilitar a demonstração e testes do projeto e acesso rápido a documentação da API, ele foi implantado no Heroku e pode ser testado através da interface do swagger:

https://desafio-impa.herokuapp.com/swagger-ui.html

Para testes e acesso a documentação da API em ambiente local pode utilizar a url http://localhost:8080/swagger-ui.html

**Usuário pré cadastrado para testes** user_id: 4






