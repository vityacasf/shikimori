create table role
(
    id         BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    role_name       TEXT UNIQUE NOT NULL
);

INSERT INTO role (role_name) VALUES ('ADMIN'), ('USER');
