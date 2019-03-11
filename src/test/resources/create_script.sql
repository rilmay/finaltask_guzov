CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL UNIQUE ,
  `password` CHAR(64) NOT NULL,
  `role` ENUM('user', 'admin') NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `registration_date` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL UNIQUE ,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `wanted_person` (
  `id` INT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `person_status` ENUM('missing', 'wanted', 'caught', 'found') NOT NULL,
  `description` TEXT(200) NULL,
  `birth_place` VARCHAR(45) NULL,
  `birth_date` DATE NULL,
  `search_area` VARCHAR(45) NULL,
  `special_signs` TEXT(170) NULL,
  `photo` VARCHAR(45) NULL,
  `pending` TINYINT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE IF NOT EXISTS `request` (
  `id` INT NULL AUTO_INCREMENT,
  `reward` INT NOT NULL,
  `application_date` DATE NOT NULL,
  `lead_date` DATE NOT NULL,
  `request_status` ENUM('cancelled', 'approved', 'expired', 'pending', 'completed') NOT NULL,
  `wanted_person_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_request_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_request_wanted_person1`
    FOREIGN KEY (`wanted_person_id`)
    REFERENCES `wanted_person` (`id`)
    ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS `record` (
  `id` INT NULL AUTO_INCREMENT,
  `description` TEXT(1024) NOT NULL,
  `place` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `record_status` ENUM('relevant', 'expired') NOT NULL,
  `rating` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `photo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


