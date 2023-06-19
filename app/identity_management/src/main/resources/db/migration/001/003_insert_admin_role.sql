-- liquibase formatted sql
-- changeset dwayne_williams:003_insert_admin_role

ALTER TABLE "role"
ADD CONSTRAINT unique_role UNIQUE("name");

ALTER TABLE "user"
DROP COLUMN role;

INSERT INTO "user"
(first_name, last_name, username, email, "password", created_on)
VALUES('Admin', 'Admin', 'admin', 'admin@inventory.com','$2a$10$XF8SMuZgtwqFIbZFNkvFnudHayNMD8WW9pLm6CwGcKCnzGpoi7unG', now());


INSERT INTO "role"
("name", created_by, created_on)
VALUES('Admin', 1, now());

INSERT INTO user_role
(user_id, role_id, created_by, created_on)
VALUES((SELECT id FROM "user" WHERE username = 'admin'), (SELECT id FROM role WHERE name = 'Admin'), (SELECT id FROM "user" WHERE username = 'admin'), now());

-- rollback DELETE FROM role_permission WHERE name = 'Admin;
