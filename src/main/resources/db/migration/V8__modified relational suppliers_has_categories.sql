ALTER TABLE suppliers_categories
    DROP FOREIGN KEY fk_supcat_on_category_entity;

ALTER TABLE suppliers_categories
    DROP FOREIGN KEY fk_supcat_on_supplier_entity;

CREATE TABLE suppliers_has_categories
(
    category_id INT NOT NULL,
    supplier_id INT NOT NULL,
    CONSTRAINT pk_suppliers_has_categories PRIMARY KEY (category_id, supplier_id)
);

ALTER TABLE suppliers_has_categories
    ADD CONSTRAINT fk_suphascat_on_category_entity FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE suppliers_has_categories
    ADD CONSTRAINT fk_suphascat_on_supplier_entity FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

DROP TABLE suppliers_categories;