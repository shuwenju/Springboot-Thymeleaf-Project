-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `joined_since` DATE NULL,
  `profile_pic` VARCHAR(255) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`follower`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`follower` (
  `relation_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  PRIMARY KEY (`relation_id`),
  INDEX `fk_follower_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_follower_user1_idx` (`friend_id` ASC) VISIBLE,
  CONSTRAINT `fk_follower_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_follower_user1`
    FOREIGN KEY (`friend_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`following`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`following` (
  `relation_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `follower_id` INT NOT NULL,
  PRIMARY KEY (`relation_id`),
  INDEX `fk_following_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_following_user2_idx` (`follower_id` ASC) VISIBLE,
  CONSTRAINT `fk_following_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_following_user2`
    FOREIGN KEY (`follower_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`post` (
  `post_id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `user_user_id` INT NOT NULL,
  PRIMARY KEY (`post_id`),
  INDEX `fk_post_user1_idx` (`user_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `post_post_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_post1_idx` (`post_post_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_post_id`)
    REFERENCES `mydb`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`like_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`like_post` (
  `like_id` INT NOT NULL AUTO_INCREMENT,
  `user_user_id` INT NOT NULL,
  `post_post_id` INT NOT NULL,
  PRIMARY KEY (`like_id`),
  INDEX `fk_like_post_user1_idx` (`user_user_id` ASC) VISIBLE,
  INDEX `fk_like_post_post1_idx` (`post_post_id` ASC) VISIBLE,
  CONSTRAINT `fk_like_post_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_post_post1`
    FOREIGN KEY (`post_post_id`)
    REFERENCES `mydb`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`like_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`like_comment` (
  `like_id` INT NOT NULL,
  `comment_comment_id` INT NOT NULL,
  `user_user_id` INT NOT NULL,
  PRIMARY KEY (`like_id`),
  INDEX `fk_like_comment_comment1_idx` (`comment_comment_id` ASC) VISIBLE,
  INDEX `fk_like_comment_user1_idx` (`user_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_like_comment_comment1`
    FOREIGN KEY (`comment_comment_id`)
    REFERENCES `mydb`.`comment` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_comment_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
