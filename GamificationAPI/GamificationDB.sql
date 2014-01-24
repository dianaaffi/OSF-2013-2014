SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `GamificationDB` ;
CREATE SCHEMA IF NOT EXISTS `GamificationDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `GamificationDB` ;

-- -----------------------------------------------------
-- Table `GamificationDB`.`Application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Application` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Application` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `apiKey` VARCHAR(45) NOT NULL ,
  `apiSecret` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Player` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Player` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `userType` VARCHAR(45) NOT NULL ,
  `application` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Player_Application1_idx` (`application` ASC) ,
  CONSTRAINT `fk_Player_Application1`
    FOREIGN KEY (`application` )
    REFERENCES `GamificationDB`.`Application` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`TeamRole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`TeamRole` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`TeamRole` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`TeamPlayer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`TeamPlayer` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`TeamPlayer` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `playerId` INT NOT NULL ,
  `teamId` INT NOT NULL ,
  `teamRole` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_TeamPlayer_Player_idx` (`playerId` ASC) ,
  INDEX `fk_TeamPlayer_Player1_idx` (`teamId` ASC) ,
  INDEX `fk_TeamPlayer_TeamRole1_idx` (`teamRole` ASC) ,
  CONSTRAINT `fk_TeamPlayer_Player`
    FOREIGN KEY (`playerId` )
    REFERENCES `GamificationDB`.`Player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TeamPlayer_Player1`
    FOREIGN KEY (`teamId` )
    REFERENCES `GamificationDB`.`Player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TeamPlayer_TeamRole1`
    FOREIGN KEY (`teamRole` )
    REFERENCES `GamificationDB`.`TeamRole` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Reward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Reward` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Reward` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `descriptiom` TEXT NULL ,
  `application` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Reward_Application1_idx` (`application` ASC) ,
  CONSTRAINT `fk_Reward_Application1`
    FOREIGN KEY (`application` )
    REFERENCES `GamificationDB`.`Application` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Badge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Badge` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Badge` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `icon` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_badge_id_reward_id`
    FOREIGN KEY (`id` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Point`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Point` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Point` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `currency` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Point_Reward1`
    FOREIGN KEY (`id` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Voucher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Voucher` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Voucher` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `validityDateFrom` DATETIME NULL ,
  `validityDateTo` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Voucher_Reward1`
    FOREIGN KEY (`id` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Quest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Quest` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Quest` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `maxLevel` INT NOT NULL ,
  `validityDateFrom` DATETIME NULL ,
  `validityDateTo` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Quest_Reward1`
    FOREIGN KEY (`id` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Privilege`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Privilege` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Privilege` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `icon` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Privilege_Reward1`
    FOREIGN KEY (`id` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`PlayerReward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`PlayerReward` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`PlayerReward` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attributionDate` DATETIME NOT NULL ,
  `currentLevel` FLOAT NULL ,
  `completionDate` DATETIME NULL ,
  `player` INT NOT NULL ,
  `reward` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_playerReward_Player1_idx` (`player` ASC) ,
  INDEX `fk_playerReward_Reward1_idx` (`reward` ASC) ,
  CONSTRAINT `fk_playerReward_Player1`
    FOREIGN KEY (`player` )
    REFERENCES `GamificationDB`.`Player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_playerReward_Reward1`
    FOREIGN KEY (`reward` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`ObjectType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`ObjectType` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`ObjectType` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  `application` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_ObjectType_Application1_idx` (`application` ASC) ,
  CONSTRAINT `fk_ObjectType_Application1`
    FOREIGN KEY (`application` )
    REFERENCES `GamificationDB`.`Application` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Object`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Object` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Object` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `objectType` INT NOT NULL ,
  `player` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Object_ObjectType1_idx` (`objectType` ASC) ,
  INDEX `fk_Object_Player1_idx` (`player` ASC) ,
  CONSTRAINT `fk_Object_ObjectType1`
    FOREIGN KEY (`objectType` )
    REFERENCES `GamificationDB`.`ObjectType` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Object_Player1`
    FOREIGN KEY (`player` )
    REFERENCES `GamificationDB`.`Player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`EventType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`EventType` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`EventType` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NOT NULL ,
  `application` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_EventType_Application1_idx` (`application` ASC) ,
  CONSTRAINT `fk_EventType_Application1`
    FOREIGN KEY (`application` )
    REFERENCES `GamificationDB`.`Application` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Event` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Event` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `object` INT NOT NULL ,
  `player` INT NOT NULL ,
  `date` DATETIME NOT NULL ,
  `eventType` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Event_Object1_idx` (`object` ASC) ,
  INDEX `fk_Event_Player1_idx` (`player` ASC) ,
  INDEX `fk_Event_EventType1_idx` (`eventType` ASC) ,
  CONSTRAINT `fk_Event_Object1`
    FOREIGN KEY (`object` )
    REFERENCES `GamificationDB`.`Object` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Event_Player1`
    FOREIGN KEY (`player` )
    REFERENCES `GamificationDB`.`Player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Event_EventType1`
    FOREIGN KEY (`eventType` )
    REFERENCES `GamificationDB`.`EventType` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamificationDB`.`Action`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GamificationDB`.`Action` ;

CREATE  TABLE IF NOT EXISTS `GamificationDB`.`Action` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `application` INT NOT NULL ,
  `objectType` INT NULL ,
  `eventType` INT NOT NULL ,
  `reward` INT NOT NULL ,
  `inputReward` INT NULL ,
  `inputValue` VARCHAR(45) NULL ,
  `outputValue` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Action_Application1_idx` (`application` ASC) ,
  INDEX `fk_Action_ObjectType1_idx` (`objectType` ASC) ,
  INDEX `fk_Action_EventType1_idx` (`eventType` ASC) ,
  INDEX `fk_Action_Reward1_idx` (`reward` ASC) ,
  INDEX `fk_Action_Reward2_idx` (`inputReward` ASC) ,
  CONSTRAINT `fk_Action_Application1`
    FOREIGN KEY (`application` )
    REFERENCES `GamificationDB`.`Application` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Action_ObjectType1`
    FOREIGN KEY (`objectType` )
    REFERENCES `GamificationDB`.`ObjectType` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Action_EventType1`
    FOREIGN KEY (`eventType` )
    REFERENCES `GamificationDB`.`EventType` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Action_Reward1`
    FOREIGN KEY (`reward` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Action_Reward2`
    FOREIGN KEY (`inputReward` )
    REFERENCES `GamificationDB`.`Reward` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `GamificationDB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
