DROP TABLE application_user;
DROP TABLE book;
DROP TABLE category;

CREATE TABLE category (
    categoryid BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(150) NOT NULL,
    publication_year INT,
	categoryid BIGINT REFERENCES category(categoryid)
);

CREATE TABLE application_user (
    id BIGSERIAL PRIMARY KEY,
    role VARCHAR(100) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

