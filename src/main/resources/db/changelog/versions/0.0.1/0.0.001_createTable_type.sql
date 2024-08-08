--liquibase formatted sql

--changeset bsprotte:1 labels:0.0.1
CREATE TABLE type (
    id INTEGER NOT NULL,

    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
)
