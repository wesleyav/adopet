# Alura Challenge Back-End 6



<!-- ![](https://www.alura.com.br/assets/img/challenges/logos/logo-challenges-back-end.1680020826.svg) -->

## Descrição

<p align=justify>
Este é um projeto desenvolvido durante o Alura Challenge Backend 6ª edição.
Consiste em uma plataforma denominada Adopet, criada para conectar pessoas que desejam adotar animais de estimação em abrigos. A plataforma deve permitir ao usuário criar um perfil e visualizar os pets na fila de adoção assim como os abrigos/ONG´s também podem criar um perfil e concretizarem a adoção. Este Challenge foi dividido em 3 fases que foram distribuídas em 4 semanas, onde a partir de uma abstração do Front-End de um arquivo no Figma foi desenvolvida a API Adopet.
</p>

### Pré-requisitos
* Java JDK 11
* STS ou Visual Studio Code
* Docker

### Tecnologias utilizadas

| Tecnologia  | Versão  |
|---|---|
| Spring  | 2.7.10  |
| H2 Database  | 2.1.214  |
| Springdoc Open API  | 1.6.15  |
| MySQL Database | 8.0
| Maven | 3.9.0 |

### Modelo Entidade Relacionamento (MER)
![](/src/main/resources/docs/img/graphql-mer.png)

### Banco de dados MySQL
#### Cria banco de dados sakila
```sql
CREATE DATABASE `sakila` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
```
#### Cria as tabelas:
```sql
-- sakila.country definition

CREATE TABLE `country` (
  `country_id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(50) NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- sakila.city definition

CREATE TABLE `city` (
  `city_id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `country_id` smallint unsigned NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`city_id`),
  KEY `idx_fk_country_id` (`country_id`),
  CONSTRAINT `fk_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

#### Adiciona registros na tabela Country:
```sql
INSERT INTO sakila.country (country_id, country, last_update) VALUES(1, 'Afghanistan', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(2, 'Algeria', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(3, 'American Samoa', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(4, 'Angola', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(5, 'Anguilla', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(6, 'Argentina', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(7, 'Armenia', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(8, 'Australia', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(9, 'Austria', '2006-02-15 04:44:00');
INSERT INTO sakila.country (country_id, country, last_update) VALUES(10, 'Azerbaijan', '2006-02-15 04:44:00');
```

#### Adiciona registros na tabela City:
```sql
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(1, 'A Corua (La Corua)', 87, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(2, 'Abha', 82, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(3, 'Abu Dhabi', 101, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(4, 'Acua', 60, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(5, 'Adana', 97, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(6, 'Addis Abeba', 31, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(7, 'Aden', 107, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(8, 'Adoni', 44, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(9, 'Ahmadnagar', 44, '2006-02-15 04:45:25');
INSERT INTO sakila.city (city_id, city, country_id, last_update) VALUES(10, 'Akishima', 50, '2006-02-15 04:45:25');
```

### Dependências do projeto (poom.xml)
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>${springdoc.version}</version>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
  <dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
</dependencies>
```

### Como rodar a aplicação:
Clone o projeto:
```git
git clone https://github.com/wesleyav/adopet.git
```
Na raiz do projeto, execute:
```bash
mvn spring-boot: run
```

### Endpoints
Esse projeto possui 3 endpoints que podem ser customizados no arquivo application-dev.properties conforme informações disponiveis em https://github.com/graphql-java-kickstart/graphql-spring-boot.

/graphql - endpoint para solicitações via POST
/graphiql - endpoint para solicitações via GET (console web)
/playground - endpoint para solicitações via GET (console web)

#### Acessando o console GraphiQL
No browser, acesse http://localhost:8085/graphiql

![](/src/main/resources/docs/img/graphiql.png)
<br>

##### Query cidade por Id:

![](/src/main/resources/docs/img/graphiql-query-cityById.png)
<br>

##### Query cidades:

![](/src/main/resources/docs/img/graphiql-query-cities.png)
<br>

#### Acessando o console Playground
No browser, acesse http://localhost:8085/playground

![](/src/main/resources/docs/img/playground.png)
<br>

##### Query cidade por Id:

![](/src/main/resources/docs/img/playground-query-cityById.png)
<br>

##### Query cidades:

![](/src/main/resources/docs/img/playground-query-cities.png)
<br>

#### Acessando via Postman

##### Query cidade por Id:
![](/src/main/resources/docs/img/postman-query-cityById.png)
<br>

##### Query cidades:
![](/src/main/resources/docs/img/postman-query-cities.png)
<br>

## Licença 

The [MIT License]() (MIT)
Copyright :copyright: 2022 - Projeto básico usando GraphQL e Spring Boot