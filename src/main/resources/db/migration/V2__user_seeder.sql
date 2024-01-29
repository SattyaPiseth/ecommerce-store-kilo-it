insert into role (name, code, created_by, updated_by, created_at, updated_at)
values ('Super-Admin', 'super-admin', null, null, current_timestamp, current_timestamp);

insert into permission (id, name, module)
values (1, 'list-role', 'role'),
       (2, 'create-role', 'role'),
       (3, 'edit-role', 'role'),
       (4, 'delete-role', 'role'),
       (5, 'list-user', 'user'),
       (6, 'create-user', 'user'),
       (7, 'edit-user', 'user'),
       (8, 'delete-user', 'user');


insert into roles_has_permissions (permission_id, role_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1);


insert into users (id, username, password, email, name, bio, avatar, address, phone, status, role_id, created_by,
                   updated_by, created_at, updated_at)
values (1, 'sombath', '{bcrypt}$2a$12$YzvNP/1D4Bv4S9SYF/PO6.J8nZnAwI3rAfaC.iEKOIkJsA2pz7JI6', 'sombath@gmail.com', 'KHOEM Sombath', 'senior back-end developer', null, 'Phnom Penh',
        '0123456789', 1, 1, null, null, current_timestamp, current_timestamp);