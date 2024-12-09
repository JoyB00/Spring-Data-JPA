create database belajar_spring_data_jpa;

use belajar_spring_data_jpa

CREATE TABLE categories
(
    id   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
)ENGINE = InnoDB;

SELECT * FROM categories

Alter table categories
ADD COLUMN created_date TIMESTAMP

Alter table categories
ADD COLUMN last_modified_date TIMESTAMP


CREATE TABLE products
(
    id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    price       BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY fk_products_categories (category_id) REFERENCES categories (id)
)ENGINE = InnoDB;

SELECT * FROM products