ALTER TABLE categories_brands
    DROP FOREIGN KEY fk_catbra_on_brand_entity;

ALTER TABLE categories_brands
    DROP FOREIGN KEY fk_catbra_on_category_entity;

CREATE TABLE categories_has_brands
(
    brand_id    INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT pk_categories_has_brands PRIMARY KEY (brand_id, category_id)
);

ALTER TABLE categories_has_brands
    ADD CONSTRAINT fk_cathasbra_on_brand_entity FOREIGN KEY (brand_id) REFERENCES brands (id);

ALTER TABLE categories_has_brands
    ADD CONSTRAINT fk_cathasbra_on_category_entity FOREIGN KEY (category_id) REFERENCES categories (id);

DROP TABLE categories_brands;