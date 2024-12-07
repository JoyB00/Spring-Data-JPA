create database belajar_spring_data_jpa;

use belajar_spring_data_jpa

CREATE TABLE categories
(
    id   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
)ENGINE = InnoDB;

SELECT * FROM categories