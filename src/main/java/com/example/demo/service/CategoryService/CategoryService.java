package com.example.demo.service.CategoryService;

import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.StructureRS;
import com.example.demo.model.request.Category.CategoryRQ;

public interface CategoryService {
    /**
     * For Category Interface
     */
    StructureRS getCategory(BaseListingRQ baseListingRQ);
    void addCategory(CategoryRQ categoryRQ);
    void updateCategory( Integer id , CategoryRQ categoryRQ);
    void deleteCategory( Integer id);
}