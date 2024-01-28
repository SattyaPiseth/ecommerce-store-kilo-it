CREATE TABLE categories
(
    id            INT AUTO_INCREMENT NOT NULL,
    uuid          VARCHAR(255)       NOT NULL,
    name          VARCHAR(255)       NOT NULL,
    `description` TEXT               NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    uuid          VARCHAR(255)          NOT NULL,
    code          VARCHAR(30)           NOT NULL,
    name          VARCHAR(150)          NULL,
    `description` TEXT                  NULL,
    image_url     VARCHAR(255)          NULL,
    unit_price    DOUBLE                NULL,
    cate_id       INT                   NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_uuid UNIQUE (uuid);

ALTER TABLE products
    ADD CONSTRAINT uc_products_code UNIQUE (code);

ALTER TABLE products
    ADD CONSTRAINT uc_products_uuid UNIQUE (uuid);

CREATE UNIQUE INDEX idx_category_name ON categories (name);

CREATE UNIQUE INDEX idx_product_code ON products (code);

CREATE UNIQUE INDEX idx_product_name ON products (name);

CREATE UNIQUE INDEX idx_product_uuid ON products (uuid);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATE FOREIGN KEY (cate_id) REFERENCES categories (id);