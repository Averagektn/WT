-- MySQL Script generated by MySQL Workbench
-- Sun Nov  5 14:25:06 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mycoolstore
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mycoolstore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mycoolstore` DEFAULT CHARACTER SET utf8mb4 ;
USE `mycoolstore` ;

-- -----------------------------------------------------
-- Table `mycoolstore`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`user` (
  `usr_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `usr_email` VARCHAR(254) NOT NULL,
  `usr_password` VARCHAR(256) NOT NULL,
  `usr_role` ENUM("admin", "customer") NOT NULL,
  `usr_banned_by` INT UNSIGNED NULL,
  PRIMARY KEY (`usr_id`),
  INDEX `fk_user_user1_idx` (`usr_banned_by` ASC) VISIBLE,
  CONSTRAINT `fk_user_user1`
    FOREIGN KEY (`usr_banned_by`)
    REFERENCES `mycoolstore`.`user` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mycoolstore`.`film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`film` (
  `flm_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `flm_description` TEXT NOT NULL,
  `flm_price` DECIMAL(5,2) NOT NULL,
  `flm_media` INT UNSIGNED NOT NULL,
  `flm_discount` TINYINT UNSIGNED NULL,
  `flm_author` VARCHAR(45) NOT NULL,
  `flm_age` ENUM("0+", "3+", "6+", "12+", "18+", "21+") NOT NULL,
  PRIMARY KEY (`flm_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mycoolstore`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`feedback` (
  `fbk_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fbk_author` INT UNSIGNED NOT NULL,
  `fbk_film` INT UNSIGNED NOT NULL,
  `fbk_text` TEXT NOT NULL,
  `fbk_rating` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`fbk_id`),
  INDEX `fk_feedback_user1_idx` (`fbk_author` ASC) VISIBLE,
  INDEX `fk_feedback_film1_idx` (`fbk_film` ASC) VISIBLE,
  CONSTRAINT `fk_feedback_user1`
    FOREIGN KEY (`fbk_author`)
    REFERENCES `mycoolstore`.`user` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_feedback_film1`
    FOREIGN KEY (`fbk_film`)
    REFERENCES `mycoolstore`.`film` (`flm_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mycoolstore`.`film_media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`film_media` (
  `fm_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fm_film_path` VARCHAR(255) NOT NULL,
  `fm_trailer_path` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`fm_id`),
  CONSTRAINT `fk_film_media_film1`
    FOREIGN KEY (`fm_id`)
    REFERENCES `mycoolstore`.`film` (`flm_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mycoolstore`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`cart` (
  `crt_user` INT UNSIGNED NOT NULL,
  `crt_film` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`crt_user`, `crt_film`),
  INDEX `fk_cart_film1_idx` (`crt_film` ASC) VISIBLE,
  CONSTRAINT `fk_cart_user`
    FOREIGN KEY (`crt_user`)
    REFERENCES `mycoolstore`.`user` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_film1`
    FOREIGN KEY (`crt_film`)
    REFERENCES `mycoolstore`.`film` (`flm_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mycoolstore`.`user_film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`user_film` (
  `uf_user` INT UNSIGNED NOT NULL,
  `uf_film` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`uf_user`, `uf_film`),
  INDEX `fk_user_film_film1_idx` (`uf_film` ASC) VISIBLE,
  CONSTRAINT `fk_user_film_user1`
    FOREIGN KEY (`uf_user`)
    REFERENCES `mycoolstore`.`user` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_film_film1`
    FOREIGN KEY (`uf_film`)
    REFERENCES `mycoolstore`.`film` (`flm_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mycoolstore`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`category` (
  `cat_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cat_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cat_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mycoolstore`.`m2m_film_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mycoolstore`.`m2m_film_category` (
  `fc_film` INT UNSIGNED NOT NULL,
  `fc_category` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`fc_film`, `fc_category`),
  INDEX `fk_film_has_category_category1_idx` (`fc_category` ASC) VISIBLE,
  INDEX `fk_film_has_category_film1_idx` (`fc_film` ASC) VISIBLE,
  CONSTRAINT `fk_film_has_category_film1`
    FOREIGN KEY (`fc_film`)
    REFERENCES `mycoolstore`.`film` (`flm_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_film_has_category_category1`
    FOREIGN KEY (`fc_category`)
    REFERENCES `mycoolstore`.`category` (`cat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
