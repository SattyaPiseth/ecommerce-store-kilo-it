ALTER TABLE `role`
    ADD updated_at datetime NULL;

ALTER TABLE `role`
    ADD updated_by BIGINT NULL;

ALTER TABLE `role`
    ADD CONSTRAINT FK_ROLE_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE `role`
DROP
COLUMN modified_at;

ALTER TABLE `role`
DROP
COLUMN modified_by;

ALTER TABLE `role`
DROP
COLUMN created_by;

ALTER TABLE `role`
    ADD created_by BIGINT NULL;

ALTER TABLE `role`
    ADD CONSTRAINT FK_ROLE_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);