-- Active: 1744089635391@@127.0.0.1@3306@depense
CREATE DATABASE depense;

USE depense;

CREATE TABLE credit(
    idCredit INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(255),
    montant FLOAT
);

CREATE TABLE depense(
    idDepense INT AUTO_INCREMENT PRIMARY KEY,
    idCredit INT,
    montantDepense FLOAT,
    FOREIGN KEY (idCredit) REFERENCES credit(idCredit)
);


INSERT INTO credit(libelle ,montant)VALUES 
('Frais' , 10000),
('Nouriture' , 50000);

INSERT INTO depense(idCredit,montantDepense) VALUES
(1,2000),
(1,5000);
