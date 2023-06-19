-- liquibase formatted sql
-- changeset dwayne_williams:004_insert_roles_permission

INSERT INTO public."permission"
(name,description)
VALUES
('role:read','The enables a user to view roles and permissions within the system'),
('role:write','The enables a user to create new roles'),
('role:update','The enables a use to update roles, assign and unassigned permissions'),
('role:delete','This enables a user to delete a role')
;

