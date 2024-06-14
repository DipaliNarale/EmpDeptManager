-- schema.sql

CREATE TABLE IF NOT EXISTS department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    salary DOUBLE,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES department(id)
);