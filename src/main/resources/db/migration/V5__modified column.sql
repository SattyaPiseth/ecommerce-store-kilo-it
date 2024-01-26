ALTER TABLE users
    ADD `6_digits` VARCHAR(6) NULL;

ALTER TABLE users
    ADD deleted BIT(1) DEFAULT 0 NULL;

ALTER TABLE users
    ADD uuid VARCHAR(255) NULL;

ALTER TABLE users
    ADD verification_token VARCHAR(255) NULL;

ALTER TABLE users
    ADD verified BIT(1) DEFAULT 0 NULL;

ALTER TABLE users
    ADD CONSTRAINT uc_users_uuid UNIQUE (uuid);

ALTER TABLE users
    MODIFY email VARCHAR (100) NOT NULL;