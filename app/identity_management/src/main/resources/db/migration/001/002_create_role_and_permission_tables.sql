-- liquibase formatted sql
-- changeset dwayne_williams:002_create_role_and_permission_tables

 CREATE TABLE role(
    id          SERIAL          NOT NULL PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL,
    created_by  INTEGER         NOT NULL ,
    created_on  TIMESTAMP       NOT NULL,
    modified_by INTEGER         NULL,
    modified_on TIMESTAMP       NULL,

    CONSTRAINT fk_created_by FOREIGN KEY(created_by) REFERENCES "user"(id),
    CONSTRAINT fk_modified_by FOREIGN KEY(modified_by) REFERENCES "user"(id)
 );

 CREATE TABLE permission(
    id              SERIAL          NOT NULL PRIMARY KEY,
    name            VARCHAR(30)     NOT NULL,
    description     VARCHAR(255)    NOT NULL,
    created_on      TIMESTAMP       NOT NULL default now()

 );

 CREATE TABLE user_role(
    user_id         INTEGER         NOT NULL,
    role_id         INTEGER         NOT NULL,
    created_by      INTEGER         NOT NULL,
    created_on      TIMESTAMP       NOT NULL,
    modified_by     INTEGER         NULL,
    modified_on     TIMESTAMP       NULL ,

    CONSTRAINT fk_created_by FOREIGN KEY(created_by) REFERENCES "user"(id),
    CONSTRAINT fk_modified_by FOREIGN KEY(modified_by) REFERENCES "user"(id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES "user"(id),
    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES role(id)
 );

  CREATE TABLE role_permission(
    role_id         INTEGER         NOT NULL,
    permission_id   INTEGER         NOT NULL ,

    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES role(id),
    CONSTRAINT fk_permission FOREIGN KEY(permission_id) REFERENCES permission(id)

 );

 -- rollback DROP TABLE role_permission;
 -- rollback DROP TABLE user_role;
 -- rollback DROP TABLE permission;
 -- rollback DROP TABLE role;
