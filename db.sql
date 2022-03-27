-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema users_management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema users_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `users_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `users_management` ;

-- -----------------------------------------------------
-- Table `users_management`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users_management`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` BIGINT NULL DEFAULT NULL,
  `gender` CHAR(1) NOT NULL,
  `dob` DATE NOT NULL,
  `pass` VARCHAR(12) NOT NULL,
  `security_ans` VARCHAR(45) NOT NULL,
  `user_profile` BLOB NULL DEFAULT NULL,
  `CreatedTime` TIMESTAMP NULL DEFAULT '0000-00-00 00:00:00',
  `ModifiedTime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `users_management`.`assignned_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users_management`.`assignned_roles` (
  `u_id` INT NOT NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`u_id`),
  CONSTRAINT `u_id`
    FOREIGN KEY (`u_id`)
    REFERENCES `users_management`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `users_management`.`role_designation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users_management`.`role_designation` (
  `role` INT NOT NULL AUTO_INCREMENT,
  `role_designation` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`role`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `users_management`.`user_addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users_management`.`user_addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `address_line1` VARCHAR(100) NOT NULL,
  `address_line2` VARCHAR(100) NULL DEFAULT NULL,
  `city` VARCHAR(20) NOT NULL,
  `pincode` INT NOT NULL,
  `state` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `users_management`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `users_management`;

DELIMITER $$
USE `users_management`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `users_management`.`addnumber`
BEFORE INSERT ON `users_management`.`users`
FOR EACH ROW
set new.phone = new.phone+1000$$

USE `users_management`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `users_management`.`users_insert_trigger`
BEFORE INSERT ON `users_management`.`users`
FOR EACH ROW
BEGIN
IF NEW.CreatedTime = '0000-00-00 00:00:00' THEN
SET NEW.CreatedTime = NOW();
END IF;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
