DROP DATABASE insurance_1;

CREATE DATABASE IF NOT EXISTS insurance_1;

USE insurance_1

CREATE TABLE IF NOT EXISTS contracts (
  id BIGINT NOT NULL AUTO_INCREMENT,
  number VARCHAR(10) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS insured_people (
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(20) DEFAULT NULL,
  last_name VARCHAR(20) DEFAULT NULL,
  middle_name VARCHAR(20) DEFAULT NULL,
  INN VARCHAR(12) DEFAULT NULL,
  price DOUBLE DEFAULT NULL,
  number_contract VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO contracts(number)
VALUES
('1'),
('2'),
('10');

INSERT INTO insured_people(first_name, last_name, middle_name, INN, price, number_contract) 
VALUES
('Alex', 'Gnilitsly', '', '1', 10000.0, '1'),
('Alex', 'Gnilitsly', 'gsb', '2', 10000.0, '1'),
('Alex', 'Gnilitsly', 'sbg', '3', 10000.0, '1'),
('Alex', 'Gnilitsly', 'vsb', '4', 10000.0, '1'),
('Alex', 'Gnilitsly', 'aaa10', '10', 10000.0, '2'),
('Alex', 'Gnilitsly', 'bbb11', '11', 10000.0, '2');
