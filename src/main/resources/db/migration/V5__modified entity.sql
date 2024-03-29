CREATE TABLE brands
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime NULL,
    created_by BIGINT NULL,
    updated_by BIGINT NULL,
    uuid       VARCHAR(255) NOT NULL,
    brand_name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_brands PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime     NOT NULL,
    updated_at    datetime NULL,
    created_by    BIGINT NULL,
    updated_by    BIGINT NULL,
    uuid          VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    `description` TEXT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE categories_has_brands
(
    brand_id    BIGINT NOT NULL,
    category_id BIGINT NOT NULL
);

CREATE TABLE inventories
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime       NOT NULL,
    updated_at        datetime NULL,
    created_by        BIGINT NULL,
    updated_by        BIGINT NULL,
    unit_cost         DECIMAL(10, 2) NOT NULL,
    quantity          INT            NOT NULL,
    location          VARCHAR(200)   NOT NULL,
    last_restock_date date           NOT NULL,
    status            VARCHAR(255)   NOT NULL,
    pro_id            BIGINT NULL,
    supplier_id       BIGINT NULL,
    store_id          BIGINT NULL,
    CONSTRAINT pk_inventories PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime     NOT NULL,
    updated_at    datetime NULL,
    created_by    BIGINT NULL,
    updated_by    BIGINT NULL,
    uuid          VARCHAR(255) NOT NULL,
    code          VARCHAR(30)  NOT NULL,
    name          VARCHAR(150) NULL,
    `description` TEXT NULL,
    image_url     VARCHAR(255) NULL,
    unit_price    DECIMAL NULL,
    cate_id       BIGINT NULL,
    brand_id      BIGINT NULL,
    supplier_id   BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE stores
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime     NOT NULL,
    updated_at   datetime NULL,
    created_by   BIGINT NULL,
    updated_by   BIGINT NULL,
    uuid         VARCHAR(255) NOT NULL,
    store_name   VARCHAR(50)  NOT NULL,
    phone_number VARCHAR(50)  NOT NULL,
    email        VARCHAR(100) NOT NULL,
    address      VARCHAR(200) NOT NULL,
    city         VARCHAR(100) NOT NULL,
    country      VARCHAR(50)  NOT NULL,
    zip_code     VARCHAR(10) NULL,
    CONSTRAINT pk_stores PRIMARY KEY (id)
);

CREATE TABLE suppliers
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime     NOT NULL,
    updated_at    datetime NULL,
    created_by    BIGINT NULL,
    updated_by    BIGINT NULL,
    uuid          VARCHAR(255) NOT NULL,
    company_name  VARCHAR(150) NOT NULL,
    address       VARCHAR(200) NOT NULL,
    city          VARCHAR(100) NOT NULL,
    country       VARCHAR(50)  NOT NULL,
    contact_email VARCHAR(100) NOT NULL,
    contact_phone VARCHAR(50)  NOT NULL,
    CONSTRAINT pk_suppliers PRIMARY KEY (id)
);

CREATE TABLE suppliers_has_categories
(
    category_id BIGINT NOT NULL,
    supplier_id BIGINT NOT NULL
);

ALTER TABLE brands
    ADD CONSTRAINT uc_brands_uuid UNIQUE (uuid);

ALTER TABLE categories
    ADD CONSTRAINT uc_categories_uuid UNIQUE (uuid);

ALTER TABLE products
    ADD CONSTRAINT uc_products_code UNIQUE (code);

ALTER TABLE products
    ADD CONSTRAINT uc_products_uuid UNIQUE (uuid);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_email UNIQUE (email);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_phone_number UNIQUE (phone_number);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_store_name UNIQUE (store_name);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_uuid UNIQUE (uuid);

ALTER TABLE suppliers
    ADD CONSTRAINT uc_suppliers_company_name UNIQUE (company_name);

ALTER TABLE suppliers
    ADD CONSTRAINT uc_suppliers_contact_email UNIQUE (contact_email);

ALTER TABLE suppliers
    ADD CONSTRAINT uc_suppliers_uuid UNIQUE (uuid);

CREATE UNIQUE INDEX idx_brand_name ON brands (brand_name);

CREATE UNIQUE INDEX idx_category_name ON categories (name);

CREATE UNIQUE INDEX idx_email ON stores (email);

CREATE INDEX idx_last_restock_date ON inventories (last_restock_date);

CREATE INDEX idx_location ON inventories (location);

CREATE UNIQUE INDEX idx_phone_number ON stores (phone_number);

CREATE UNIQUE INDEX idx_product_code ON products (code);

CREATE UNIQUE INDEX idx_product_name ON products (name);

CREATE UNIQUE INDEX idx_product_uuid ON products (uuid);

CREATE INDEX idx_quantity ON inventories (quantity);

CREATE INDEX idx_status ON inventories (status);

CREATE UNIQUE INDEX idx_store_name ON stores (store_name);

CREATE UNIQUE INDEX idx_supplier_company_name ON suppliers (company_name);

CREATE UNIQUE INDEX idx_supplier_contact_email ON suppliers (contact_email);

CREATE UNIQUE INDEX idx_supplier_contact_phone ON suppliers (contact_phone);

CREATE INDEX idx_unit_cost ON inventories (unit_cost);

ALTER TABLE brands
    ADD CONSTRAINT FK_BRANDS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE brands
    ADD CONSTRAINT FK_BRANDS_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE categories
    ADD CONSTRAINT FK_CATEGORIES_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE categories
    ADD CONSTRAINT FK_CATEGORIES_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_PRO FOREIGN KEY (pro_id) REFERENCES products (id);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_STORE FOREIGN KEY (store_id) REFERENCES stores (id);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brands (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATE FOREIGN KEY (cate_id) REFERENCES categories (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE stores
    ADD CONSTRAINT FK_STORES_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE stores
    ADD CONSTRAINT FK_STORES_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE suppliers
    ADD CONSTRAINT FK_SUPPLIERS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE suppliers
    ADD CONSTRAINT FK_SUPPLIERS_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);

ALTER TABLE categories_has_brands
    ADD CONSTRAINT fk_cathasbra_on_brand_entity FOREIGN KEY (brand_id) REFERENCES brands (id);

ALTER TABLE categories_has_brands
    ADD CONSTRAINT fk_cathasbra_on_category_entity FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE suppliers_has_categories
    ADD CONSTRAINT fk_suphascat_on_category_entity FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE suppliers_has_categories
    ADD CONSTRAINT fk_suphascat_on_supplier_entity FOREIGN KEY (supplier_id) REFERENCES suppliers (id);