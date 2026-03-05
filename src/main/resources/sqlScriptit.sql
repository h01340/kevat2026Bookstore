-- Poistetaan taulut oikeassa järjestyksessä riippuvuuksien takia
DROP TABLE IF EXISTS application_user;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;

--luodaan taulu
CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

--listätään pari riviä 
INSERT INTO category (name)
VALUES ('sarjakuva'),
('dekkari'),
('dokumentti');

--luodaan taulu
CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(150) NOT NULL,
    publication_year INT,
	category_id BIGINT REFERENCES category(id)
);

--lisää rivejä tauluun
INSERT INTO book (title, author, publication_year, category_id) 
VALUES 
('Mökkimaailma', 'Mari Marison',  1974, 1),
('Puutarha', 'Minni Hiiri', 1970, 1);


-- application_user-taulu
CREATE TABLE application_user (
    id BIGSERIAL PRIMARY KEY,
    role VARCHAR(100) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);


INSERT INTO application_user (username, password, role) 
VALUES 
('user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'),
('admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN');