CREATE DATABASE travel_db;
USE travel_db;

CREATE TABLE travel_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    travel_name VARCHAR(100),
    destination VARCHAR(100),
    ticket_price DOUBLE
);