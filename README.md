# Sistema de Vendas (Nome a definir) 
Versão 1.0

## Descrição
Projeto de um sistemas de vendas simples com aplicação de vários conceitos do ecossistema Spring.

## Principais tecnologias e frameworks utilizados 

### Spring Boot
o [Spring](https://spring.io/why-spring) torna a programação em Java mais rápida, fácil e segura para todos. O foco do Spring em velocidade, simplicidade e produtividade o tornou o framework Java mais popular do mundo.

### Spring Data 

A missão do [Spring Data](https://spring.io/projects/spring-data) é fornecer um modelo de programação baseado em Spring familiar e consistente para acesso a dados, enquanto ainda retém as características especiais do armazenamento de dados subjacente.

### Spring Security

[Spring Security](https://spring.io/projects/spring-security) é uma estrutura de autenticação e controle de acesso poderosa e altamente personalizável. É o padrão de fato para proteger aplicativos baseados em Spring.

### Maven 

[Apache Maven](https://maven.apache.org/) é uma ferramenta de gerenciamento e compreensão de projetos de software. Com base no conceito de modelo de objeto de projeto (POM), o Maven pode gerenciar a construção, o relatório e a documentação de um projeto a partir de uma informação central.

### Flyway

[Flyway](https://flywaydb.org/documentation/) é uma ferramenta de migração de banco de dados de código aberto. Favorece fortemente a simplicidade e a convenção sobre a configuração.

### Lombok

[Lombok](https://projectlombok.org) é uma biblioteca java que se conecta automaticamente ao seu editor e ferramentas de construção, aprimorando o seu java. Automatiza suas variáveis de registro e muito mais.

### Pré-requisitos
* Java 8

* Maven

* Docker Engine

### Para executar
* 1º passo: Abra o terminal e execute o Microsoft SQL Server com o seguinte comando:

~~~
docker run -d --name sqlserver2017 --restart=always -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=DaTaBaSe6-3-3#TSG" -p 1401:1433 mcr.microsoft.com/mssql/server:2017-latest
~~~

* 2º passo: Vá até um gerenciador de banco de dados (Dbeaver, por exemplo), conecte-se com os dados da instância do SQL Server recém criada e execute o seguinte script para criar a base de dados:

~~~~
create database sistemaVendasDB
~~~~

* 3º passo: Abra o terminal, vá até a pasta onde se encontra o projeto e execute-o com o seguinte comando:

~~~
mvn package spring-boot:run
~~~

### Documentação dos Endpoints
http://localhost:8095/sistema-vendas/swagger-ui.html

### Diagrama de Classes ###

![Diagrama de Classes](docs/diagrama-classe.png)

### Modelo Entidade Relacional

![Modelo ER](docs/modelo-er.png)


### Referências
* Spring: https://spring.io/why-spring
* Spring Data: https://spring.io/projects/spring-data
* Spring Security: https://spring.io/projects/spring-security
* Maven: https://maven.apache.org/
* Lombok: https://projectlombok.org
* Flyway: https://flywaydb.org/documentation/



