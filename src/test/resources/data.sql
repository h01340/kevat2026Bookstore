--tämä on testidataa, tiedot luodaan H2-kantaan

--listätään pari riviä 
INSERT INTO category (name)
VALUES ('Runous'),
('Sarjakuva'),
('Dokumentti'), 
('Dekkari'),
('Jokin muu');

--lisää rivejä tauluun
INSERT INTO book (title, author, publication_year, category_id) 
VALUES 
('Maaliskuun lauluja', 'Eino Leino',  1896, 1),
('Talviyö', 'Eino Leino', 1905, 1);


INSERT INTO application_user (username, password, role) 
VALUES 
('user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'),
('admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN'),
('Minna', '$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6', 'ADMIN');

--
--SELECT * FROM CATEGORY; 
--SELECT * FROM APPLICATION_USER;
--SELECT * FROM BOOK; 