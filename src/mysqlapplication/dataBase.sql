CREATE DATABASE mydatabase;

USE mydatabase;

CREATE TABLE students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  last_name VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  gender ENUM('Мужской', 'Женский') NOT NULL,
  phone VARCHAR(30),
  address VARCHAR(255) NOT NULL,
  birth_date DATE NOT NULL,
  education_place VARCHAR(255) NOT NULL,
  faculty ENUM('ФКП', 'ФИТУ', 'ФРЭ', 'ФКСиС', 'ФИБ', 'ИЭФ', 'ВФ') NOT NULL
);