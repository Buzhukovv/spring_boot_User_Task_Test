CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);
INSERT INTO users (login, password, role) VALUES ('user1', '123123', 'USER');
INSERT INTO users (login, password, role) VALUES ('user2', '123123', 'USER');

