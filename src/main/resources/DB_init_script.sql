-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TM
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TM
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TM` DEFAULT CHARACTER SET utf8 ;
USE `TM` ;

-- -----------------------------------------------------
-- Table `TM`.`unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TM`.`unit` ;

CREATE TABLE IF NOT EXISTS `TM`.`unit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TM`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TM`.`user` ;

CREATE TABLE IF NOT EXISTS `TM`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `rating` INT NOT NULL,
  `unit_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_unit_idx` (`unit_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_unit`
    FOREIGN KEY (`unit_id`)
    REFERENCES `TM`.`unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TM`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TM`.`task` ;

CREATE TABLE IF NOT EXISTS `TM`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creation_time` DATETIME NOT NULL DEFAULT NOW(),
  `theme` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `author_id` INT NOT NULL,
  `executor_id` INT NOT NULL,
  `status` ENUM('READY', 'BLOCKED', 'IN_PROGRESS', 'DONE') NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_user1_idx` (`author_id` ASC) VISIBLE,
  INDEX `fk_task_user2_idx` (`executor_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_user1`
    FOREIGN KEY (`author_id`)
    REFERENCES `TM`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_user2`
    FOREIGN KEY (`executor_id`)
    REFERENCES `TM`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TM`.`user_comments_task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TM`.`user_comments_task` ;

CREATE TABLE IF NOT EXISTS `TM`.`user_comments_task` (
  `user_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  `comment` VARCHAR(255) NOT NULL,
  INDEX `fk_user_has_task_task1_idx` (`task_id` ASC) VISIBLE,
  INDEX `fk_user_has_task_user1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`task_id`, `user_id`),
  CONSTRAINT `fk_user_has_task_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `TM`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_task_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `TM`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TM`.`attachment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TM`.`attachment` ;

CREATE TABLE IF NOT EXISTS `TM`.`attachment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reference` VARCHAR(255) NOT NULL,
  `task_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_attachment_task1_idx` (`task_id` ASC) VISIBLE,
  CONSTRAINT `fk_attachment_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `TM`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
