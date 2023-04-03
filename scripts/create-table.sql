CREATE TABLE tutor (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `telefone` VARCHAR(11) NULL,
  `cidade` VARCHAR(45) NULL,
  `sobre` VARCHAR(45) NULL,
  `created_at` DATE NULL,
  `updated_at` DATE NULL,
  `foto` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));