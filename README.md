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

| Tecnologia         | Versão  |
| ------------------ | ------- |
| Spring             | 2.7.10  |
| H2 Database        | 2.1.214 |
| Springdoc Open API | 1.6.15  |
| MySQL Database     | 8.0     |
| Maven              | 3.9.0   |

### Modelo Entidade Relacionamento (MER)
![](/src/main/resources/docs/img/mer.png)

### Banco de dados MySQL
#### Script para criação do banco de dados db_adopet
```sql
CREATE database db_adopet;
USE db_adopet;

CREATE TABLE IF NOT EXISTS `db_adopet`.`estado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`cidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `estado_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cidade_estado2_idx` (`estado_id` ASC) VISIBLE,
  CONSTRAINT `fk_cidade_estado2`
    FOREIGN KEY (`estado_id`)
    REFERENCES `db_adopet`.`estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`bairro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `cidade_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bairro_cidade2_idx` (`cidade_id` ASC) VISIBLE,
  CONSTRAINT `fk_bairro_cidade2`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `db_adopet`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(255) NULL,
  `numero` VARCHAR(45) NULL,
  `cep` VARCHAR(8) NULL,
  `bairro_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_endereco_bairro2_idx` (`bairro_id` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_bairro2`
    FOREIGN KEY (`bairro_id`)
    REFERENCES `db_adopet`.`bairro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`abrigo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `endereco_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_abrigo_endereco2_idx` (`endereco_id` ASC) VISIBLE,
  CONSTRAINT `fk_abrigo_endereco2`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `db_adopet`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`animal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `idade` VARCHAR(45) NULL,
  `descricao` VARCHAR(100) NULL,
  `adotado` TINYINT(1) NULL,
  `image_url` VARCHAR(255) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `abrigo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_animal_abrigo2_idx` (`abrigo_id` ASC) VISIBLE,
  CONSTRAINT `fk_animal_abrigo2`
    FOREIGN KEY (`abrigo_id`)
    REFERENCES `db_adopet`.`abrigo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`tutor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `telefone` VARCHAR(13) NULL,
  `sobre` VARCHAR(255) NULL,
  `image_url` VARCHAR(255) NULL,
  `cidade` VARCHAR(100) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `senha` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`adocao` (
  `id` BINARY(16) NULL,
  `tutor_id` INT NOT NULL,
  `animal_id` INT NOT NULL,
  `data_adocao` DATE NULL,
  PRIMARY KEY (`tutor_id`, `animal_id`),
  INDEX `fk_tutor_has_animal_animal1_idx` (`animal_id` ASC) VISIBLE,
  INDEX `fk_tutor_has_animal_tutor1_idx` (`tutor_id` ASC) VISIBLE,
  CONSTRAINT `fk_tutor_has_animal_tutor1`
    FOREIGN KEY (`tutor_id`)
    REFERENCES `db_adopet`.`tutor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tutor_has_animal_animal1`
    FOREIGN KEY (`animal_id`)
    REFERENCES `db_adopet`.`animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
```

### Dependências do projeto (poom.xml)
![](/src/main/resources/docs/img/dependencias.jpg)

### Como rodar a aplicação localmente:

Clone o projeto:

```git
git clone https://github.com/wesleyav/adopet.git
```
Na raiz do projeto, execute:
```bash
./mvnw spring-boot:run
```

### Como rodar a aplicação via Docker

Clone o projeto:
```bash
git clone https://github.com/wesleyav/adopet.git
```

Execute o comando:
```bash
docker-compose up
```

### Endpoints

#### Tutores
| Método HTTP | Prefixo | Endpoint   | Descrição                    |
| ----------- | ------- | ---------- | ---------------------------- |
| GET         | /api/v1 | /tutores   | Retorna uma lista de tutores |
| GET         | /api/v1 | /tutores/1 | Retorna o tutor com o id 1   |
| POST        | /api/v1 | /tutores   | Cria um tutor                |
| PUT         | /api/v1 | /tutores/1 | Atualiza o tutor com o id 1  |
| DELETE      | /api/v1 | /tutores/1 | Remove o tutor com o id 1    |

#### Abrigos
| Método HTTP | Prefixo | Endpoint   | Descrição                             |
| ----------- | ------- | ---------- | ------------------------------------- |
| GET         | /api/v1 | /abrigos   | Retorna uma lista paginada de abrigos |
| GET         | /api/v1 | /abrigos/1 | Retorna o abrigo com o id 1           |
| POST        | /api/v1 | /abrigos   | Cria um abrigo                        |
| PUT         | /api/v1 | /abrigos/1 | Atualiza o abrigo com o id 1          |
| DELETE      | /api/v1 | /abrigos/1 | Remove o abrigo com o id 1            |

#### Animais
| Método HTTP | Prefixo | Endpoint   | Descrição                             |
| ----------- | ------- | ---------- | ------------------------------------- |
| GET         | /api/v1 | /animais   | Retorna uma lista paginada de animais |
| GET         | /api/v1 | /animais/1 | Retorna o animal com o id 1           |
| POST        | /api/v1 | /animais   | Cria um animal                        |
| PUT         | /api/v1 | /animais/1 | Atualiza o animal com o id 1          |
| DELETE      | /api/v1 | /animais/1 | Remove o animal com o id 1            |

#### Adoções
| Método HTTP | Prefixo | Endpoint   | Descrição                             |
| ----------- | ------- | ---------- | ------------------------------------- |
| GET         | /api/v1 | /animais   | Retorna uma lista paginada de adoções |
| POST        | /api/v1 | /adocoes   | Realiza uma adoção                    |
| DELETE      | /api/v1 | /adocoes/1 | Remove a adoção com o id (uuid)       |

#### Acessando o Swagger:
No browser, acesse http://localhost:8080/swagger-ui/index.html

![](/src/main/resources/docs/img/swagger-endpoints.gif)
<br>


## Licença 

The [MIT License]() (MIT)
Copyright :copyright: 2022 - Adopet