CREATE SCHEMA `sqlcollegeweber` DEFAULT CHARACTER SET utf8 ;
USE sqlcollegeweber;
CREATE TABLE `sqlcollegeweber`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
CREATE TABLE `sqlcollegeweber`.`info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `age` INT NULL DEFAULT NULL,
  `data` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
