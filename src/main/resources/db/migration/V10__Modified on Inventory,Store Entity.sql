CREATE TABLE inventories
(
    inventory_id      INT AUTO_INCREMENT NOT NULL,
    unit_cost         DECIMAL(10, 2)     NOT NULL,
    quantity          INT                NOT NULL,
    location          VARCHAR(200)       NOT NULL,
    last_restock_date date               NOT NULL,
    status            VARCHAR(255)       NOT NULL,
    pro_id            BIGINT             NULL,
    supplier_id       INT                NULL,
    store_id          INT                NULL,
    CONSTRAINT pk_inventories PRIMARY KEY (inventory_id)
);

CREATE TABLE stores
(
    id           INT AUTO_INCREMENT NOT NULL,
    uuid         VARCHAR(255)       NOT NULL,
    store_name   VARCHAR(50)        NOT NULL,
    phone_number VARCHAR(50)        NOT NULL,
    email        VARCHAR(100)       NOT NULL,
    address      VARCHAR(200)       NOT NULL,
    city         VARCHAR(100)       NOT NULL,
    country      VARCHAR(50)        NOT NULL,
    zip_code     VARCHAR(10)        NULL,
    CONSTRAINT pk_stores PRIMARY KEY (id)
);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_email UNIQUE (email);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_phone_number UNIQUE (phone_number);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_store_name UNIQUE (store_name);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_uuid UNIQUE (uuid);

CREATE UNIQUE INDEX idx_email ON stores (email);

CREATE INDEX idx_last_restock_date ON inventories (last_restock_date);

CREATE INDEX idx_location ON inventories (location);

CREATE UNIQUE INDEX idx_phone_number ON stores (phone_number);

CREATE INDEX idx_quantity ON inventories (quantity);

CREATE INDEX idx_status ON inventories (status);

CREATE UNIQUE INDEX idx_store_name ON stores (store_name);

CREATE INDEX idx_unit_cost ON inventories (unit_cost);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_PRO FOREIGN KEY (pro_id) REFERENCES products (id);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_STORE FOREIGN KEY (store_id) REFERENCES stores (id);

ALTER TABLE inventories
    ADD CONSTRAINT FK_INVENTORIES_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

ALTER TABLE products
    DROP COLUMN unit_price;

ALTER TABLE products
    ADD unit_price DECIMAL NULL;