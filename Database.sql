CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` INT NOT NULL,
	`type` VARCHAR(255) NOT NULL,
	`login` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `document` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
	`type` VARCHAR(255) NOT NULL,
	`availability` BOOLEAN NOT NULL,
	`available_copies` INT NOT NULL,
	`edition` BOOLEAN,
	`publisher` VARCHAR(255),
	`editor` VARCHAR(255),
	`publication_date` DATETIME,
	`is_bestseller` BOOLEAN,
	`queue` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `author_document` (
	`author_id` INT NOT NULL,
	`document_id` INT NOT NULL
);

CREATE TABLE `author` (
	`name` VARCHAR(255) NOT NULL,
	`id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `document_user` (
	`user_id` INT NOT NULL,
	`document_id` INT NOT NULL,
	`date` TIMESTAMP NOT NULL,
	`status` VARCHAR(255) NOT NULL DEFAULT 'taken'
);

ALTER TABLE `document` ADD CONSTRAINT `document_fk0` FOREIGN KEY (`edition`) REFERENCES ``(``);

ALTER TABLE `author_document` ADD CONSTRAINT `author_document_fk0` FOREIGN KEY (`author_id`) REFERENCES `document`(`id`);

ALTER TABLE `author_document` ADD CONSTRAINT `author_document_fk1` FOREIGN KEY (`document_id`) REFERENCES `author`(`id`);

ALTER TABLE `document_user` ADD CONSTRAINT `document_user_fk0` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);

ALTER TABLE `document_user` ADD CONSTRAINT `document_user_fk1` FOREIGN KEY (`document_id`) REFERENCES `document`(`id`);

