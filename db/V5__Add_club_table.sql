create table club
(
    id BIGSERIAL    NOT NULL   UNIQUE    PRIMARY KEY,
    name TEXT   NOT NULL,
    members INT
);

INSERT INTO club (name) VALUES ('Naruto')
