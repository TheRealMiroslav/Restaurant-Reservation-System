-- Inicializace schématu (DDL)
CREATE TABLE IF NOT EXISTS customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(20),
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS restaurant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    address VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(100),
    average_rating DOUBLE DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS restaurant_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id INT,
    table_code VARCHAR(20),
    capacity INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);

CREATE TABLE IF NOT EXISTS reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    table_id INT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    notes VARCHAR(255),
    person_count INT,
    status VARCHAR(20) DEFAULT 'NEPOTVRZENA',
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (table_id) REFERENCES restaurant_table(id)
);

CREATE TABLE IF NOT EXISTS review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    restaurant_id INT,
    comment VARCHAR(255),
    rating DOUBLE,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);