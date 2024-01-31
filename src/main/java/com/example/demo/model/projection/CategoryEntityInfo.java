package com.example.demo.model.projection;

/**
 * Projection for {@link com.example.demo.db.entity.CategoryEntity}
 */
public interface CategoryEntityInfo {
    Integer getId();

    String getUuid();

    String getName();

    String getDescription();
}