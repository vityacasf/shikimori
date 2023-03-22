create table role
(
    id              BIGSERIAL   UNIQUE  NOT NULL  PRIMARY KEY,
    role_name       TEXT        UNIQUE  NOT NULL
);

INSERT INTO role (role_name) VALUES ('ADMIN'), ('USER');
