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