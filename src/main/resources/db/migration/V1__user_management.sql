CREATE TABLE permission
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    name   VARCHAR(255)          NOT NULL,
    module VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);

CREATE TABLE `role`
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)          NOT NULL,
    code       VARCHAR(255)          NULL,
    created_by BIGINT                NULL,
    updated_by BIGINT                NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE roles_has_permissions
(
    permission_id BIGINT NOT NULL,
    role_id       BIGINT NOT NULL
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    username   VARCHAR(255)          NOT NULL,
    password   VARCHAR(100)          NOT NULL,
    email      VARCHAR(100)          NULL,
    name       VARCHAR(100)          NULL,
    bio        VARCHAR(200)          NULL,
    avatar     VARCHAR(200)          NULL,
    address    VARCHAR(200)          NULL,
    phone      VARCHAR(50)           NULL,
    status     TINYINT(1) DEFAULT 1  NULL,
    role_id    BIGINT                NOT NULL,
    created_by BIGINT                NULL,
    updated_by BIGINT                NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE INDEX idx_permission_name ON permission (name);

CREATE UNIQUE INDEX idx_role_name ON `role` (name);

CREATE UNIQUE INDEX idx_user_email ON users (email);

CREATE UNIQUE INDEX idx_user_username ON users (username);

ALTER TABLE `role`
    ADD CONSTRAINT FK_ROLE_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE `role`
    ADD CONSTRAINT FK_ROLE_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE roles_has_permissions
    ADD CONSTRAINT fk_rolhasper_on_permission_entity FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE roles_has_permissions
    ADD CONSTRAINT fk_rolhasper_on_role_entity FOREIGN KEY (role_id) REFERENCES `role` (id);