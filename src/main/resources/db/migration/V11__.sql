ALTER TABLE categories
    ADD delete_at datetime NULL;

ALTER TABLE categories
    ADD deleted BIT(1) DEFAULT 0 NULL;