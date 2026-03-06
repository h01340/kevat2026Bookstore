--tämä on testidataa, tiedot luodaan H2-kantaan

INSERT INTO category (name)
VALUES ('sarjakuva'),
('dekkari'),
('dokumentti');

INSERT INTO book (title, author, publication_year, category_id) 
VALUES 
('Mökkimaailma', 'Mari Marison',  1974, 1),
('Puutarha', 'Minni Hiiri', 1970, 1);


INSERT INTO application_user (username, password, role) 
VALUES 
('user', '$2a$10$1DTvwpXVBArGFixHBuzVJObjTuXhIOkx5pse6KsYs8/C2ckxnGEou', 'USER'),
('admin', '$2a$10$cDZgyF4xaPMmmoRW3OVcmuf.8o2YSx8.M7CeRKqi.1PVw.t3E8uEC', 'ADMIN');