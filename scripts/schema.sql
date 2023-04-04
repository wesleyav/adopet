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
  `estado` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cidade_estado1_idx` (`estado` ASC) VISIBLE,
  CONSTRAINT `fk_cidade_estado1`
    FOREIGN KEY (`estado`)
    REFERENCES `db_adopet`.`estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`bairro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `cidade` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bairro_cidade1_idx` (`cidade` ASC) VISIBLE,
  CONSTRAINT `fk_bairro_cidade1`
    FOREIGN KEY (`cidade`)
    REFERENCES `db_adopet`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(45) NULL,
  `numero` VARCHAR(45) NULL,
  `cep` VARCHAR(8) NULL,
  `bairro` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_endereco_bairro1_idx` (`bairro` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_bairro1`
    FOREIGN KEY (`bairro`)
    REFERENCES `db_adopet`.`bairro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`abrigo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `endereco` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_abrigo_endereco1_idx` (`endereco` ASC) VISIBLE,
  CONSTRAINT `fk_abrigo_endereco1`
    FOREIGN KEY (`endereco`)
    REFERENCES `db_adopet`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_adopet`.`animal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `idade` INT NULL,
  `descricao` VARCHAR(100) NULL,
  `image_url` VARCHAR(100) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `abrigo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_animal_abrigo1_idx` (`abrigo` ASC) VISIBLE,
  CONSTRAINT `fk_animal_abrigo1`
    FOREIGN KEY (`abrigo`)
    REFERENCES `db_adopet`.`abrigo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;