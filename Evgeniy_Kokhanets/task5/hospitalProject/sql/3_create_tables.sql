USE `hospital_db`;

CREATE TABLE `user`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(50) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `role` INTEGER NOT NULL,
    PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `prescription`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `patient_id` BIGINT NOT NULL,
    `physician_id` BIGINT NOT NULL,
    `drugs` VARCHAR(50),
    `procedure` VARCHAR(50),
    `surgery` VARCHAR(50),
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`patient_id`) REFERENCES `user`(`id`) ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY(`physician_id`) REFERENCES `user`(`id`) ON UPDATE RESTRICT ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `sickness_record`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `medical_assessment` VARCHAR(200),
    `patient_id` BIGINT NOT NULL,
    `physician_id` BIGINT NOT NULL,
    `hospitalization_date` TIMESTAMP NOT NULL,
    `discharge_date` TIMESTAMP NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`patient_id`) REFERENCES `user`(`id`) ON UPDATE RESTRICT ON DELETE CASCADE,
    FOREIGN KEY(`physician_id`) REFERENCES `user`(`id`) ON UPDATE RESTRICT ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARACTER SET utf8;
