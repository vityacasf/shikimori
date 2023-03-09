create table users
(
    id            BIGSERIAL NOT NULL PRIMARY KEY,
    login    TEXT      NOT NULL,
    name     TEXT      NOT NULL,
    email         TEXT      NOT NULL,
    password      TEXT      NOT NULL,
    role          TEXT    NOT NULL,

    UNIQUE (email)
);
