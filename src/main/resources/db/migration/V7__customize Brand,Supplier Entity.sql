CREATE TABLE brands
(
    id         INT AUTO_INCREMENT NOT NULL,
    uuid       VARCHAR(255)       NOT NULL,
    brand_name VARCHAR(100)       NOT NULL,
    CONSTRAINT pk_brands PRIMARY KEY (id)
);

CREATE TABLE categories_brands
(
    brand_id INT NOT NULL,
    cate_id  INT NOT NULL,
    CONSTRAINT pk_categories_brands PRIMARY KEY (brand_id, cate_id)
);

CREATE TABLE suppliers
(
    id            INT AUTO_INCREMENT NOT NULL,
    uuid          VARCHAR(255)       NOT NULL,
    company_name  VARCHAR(150)       NOT NULL,
    address       VARCHAR(200)       NOT NULL,
    city          VARCHAR(100)       NOT NULL,
    country       VARCHAR(50)        NOT NULL,
    contact_email VARCHAR(100)       NOT NULL,
    contact_phone VARCHAR(50)        NOT NULL,
    CONSTRAINT pk_suppliers PRIMARY KEY (id)
);

CREATE TABLE suppliers_categories
(
    cate_id     INT NOT NULL,
    supplier_id INT NOT NULL,
    CONSTRAINT pk_suppliers_categories PRIMARY KEY (cate_id, supplier_id)
);

ALTER TABLE products
    ADD brand_id INT NULL;

ALTER TABLE products
    ADD supplier_id INT NULL;

ALTER TABLE brands
    ADD CONSTRAINT uc_brands_uuid UNIQUE (uuid);

ALTER TABLE suppliers
    ADD CONSTRAINT uc_suppliers_company_name UNIQUE (company_name);

ALTER TABLE suppliers
    ADD CONSTRAINT uc_suppliers_contact_email UNIQUE (contact_email);

ALTER TABLE suppliers
    ADD CONSTRAINT uc_suppliers_uuid UNIQUE (uuid);

CREATE UNIQUE INDEX idx_brand_name ON brands (brand_name);

CREATE UNIQUE INDEX idx_supplier_company_name ON suppliers (company_name);

CREATE UNIQUE INDEX idx_supplier_contact_email ON suppliers (contact_email);

CREATE UNIQUE INDEX idx_supplier_contact_phone ON suppliers (contact_phone);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brands (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

ALTER TABLE categories_brands
    ADD CONSTRAINT fk_catbra_on_brand_entity FOREIGN KEY (brand_id) REFERENCES brands (id);

ALTER TABLE categories_brands
    ADD CONSTRAINT fk_catbra_on_category_entity FOREIGN KEY (cate_id) REFERENCES categories (id);

ALTER TABLE suppliers_categories
    ADD CONSTRAINT fk_supcat_on_category_entity FOREIGN KEY (cate_id) REFERENCES categories (id);

ALTER TABLE suppliers_categories
    ADD CONSTRAINT fk_supcat_on_supplier_entity FOREIGN KEY (supplier_id) REFERENCES suppliers (id);