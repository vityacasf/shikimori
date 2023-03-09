create table users
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    login       TEXT      NOT NULL,
    name        TEXT      NOT NULL,
    email       TEXT      NOT NULL UNIQUE ,
    password    TEXT      NOT NULL,
    role_id     BIGINT    NOT NULL,

FOREIGN KEY (role_id) REFERENCES role (id)
);
