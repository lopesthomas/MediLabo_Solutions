-- --------------------------------------------------------
-- Script d’initialisation de la base "medilabo"
-- Contexte : exécuté au lancement du build Docker
-- --------------------------------------------------------

-- Création de la table patients
CREATE TABLE IF NOT EXISTS patients (
    id INT PRIMARY KEY,
    address VARCHAR(255),
    birth_date DATE,
    first_name VARCHAR(255),
    gender TINYINT,
    last_name VARCHAR(255),
    phone VARCHAR(255)
);

-- Création de la table de séquence
CREATE TABLE IF NOT EXISTS patients_seq (
    next_val INT
);

-- Insertion des données patients
INSERT IGNORE INTO patients (id, address, birth_date, first_name, gender, last_name, phone) VALUES
    (1,   '1 Brookside St', '1966-12-31', 'Test', 1, 'TestNone',       '100-222-3333'),
    (52,  '2 High St',      '1945-06-24', 'Test', 0, 'TestBorderline', '200-333-4444'),
    (102, '3 Club Road',    '2004-06-18', 'Test', 0, 'TestInDanger',   '300-444-5555'),
    (103, '4 Valley Dr',    '2002-06-28', 'Test', 1, 'TestEarlyOnset', '400-555-6666');

-- Initialisation de la séquence
INSERT IGNORE INTO patients_seq (next_val) VALUES (201);
