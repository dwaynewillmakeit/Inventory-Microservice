-- liquibase formatted sql
-- changeset dwayne_williams:002_create_user_table

 CREATE TABLE "user"(

    id SERIAL                  NOT NULL PRIMARY KEY,
    first_name VARCHAR(255)    NOT NULL,
    last_name VARCHAR(255)     NOT NULL,
    username VARCHAR(255)      NOT NULL,
    email VARCHAR(255)         NOT NULL,
    password VARCHAR(255)      NOT NULL,
    role     VARCHAR(255)      NOT NULL,
    created_on TIMESTAMP       NOT NULL

 );

 -- rollback DROP TABLE user;
