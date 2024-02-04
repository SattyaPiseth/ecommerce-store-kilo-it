package com.example.demo.service.CategoryService;


import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.db.entity.CategoryEntity;
import com.example.demo.db.reposiroty.CategoryRepository;
import com.example.demo.exception.httpstatus.BadRequestException;
import com.example.demo.model.projection.CategoryEntityInfo;
import com.example.demo.model.request.Category.CategoryRQ;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Service

public class CategoryServiceImplement implements CategoryService {
private final CategoryRepository categoryRepository;

    public CategoryServiceImplement(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public StructureRS getCategory(BaseListingRQ baseListingRQ) {
        Page<CategoryEntityInfo>categoryEntityInfos=categoryRepository.findByNameContainsAndIsDeletedFalse(baseListingRQ.getQuery(),baseListingRQ.getPageable() );
        categoryEntityInfos.getContent();
       return new StructureRS(categoryEntityInfos);
    }

    @Override
    @Transactional
    public void addCategory(CategoryRQ categoryRQ) {
        boolean isExisted = categoryRepository.existsByNameAndIsDeletedFalse(categoryRQ.getName());
        CategoryEntity existingDeletedCategory = categoryRepository.findByNameAndIsDeletedTrue(categoryRQ.getName());
        if (isExisted) {
            throw new BadRequestException(MessageConstant.CATEGORY.CATEGORYIDALREADYEXIST);
        } else if (existingDeletedCategory != null) {
            existingDeletedCategory.setName(categoryRQ.getName());
            existingDeletedCategory.setDescription(categoryRQ.getDescription());
            existingDeletedCategory.setIsDeleted(false);
            categoryRepository.save(existingDeletedCategory);
        } else {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setUuid(UUID.randomUUID().toString());
            categoryEntity.setName(categoryRQ.getName());
            categoryEntity.setDescription(categoryRQ.getDescription());
            categoryEntity.setIsDeleted(false);
            categoryRepository.save(categoryEntity);
        }
    }

    @Override
    @Transactional
    public void updateCategory(String uuid, CategoryRQ categoryRQ) {
        CategoryEntity categoryEntity = categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new BadRequestException(MessageConstant.CATEGORY.ITEMCATEGORYNOTFOUND));
        categoryEntity.setName(categoryRQ.getName());
        categoryEntity.setDescription(categoryRQ.getDescription());
        categoryRepository.save(categoryEntity);
    }

    @Override
    @Transactional
    public void deleteCategory(String uuid) {
        CategoryEntity categoryEntity= categoryRepository.findByUuid(uuid)
                .orElseThrow(()-> new BadRequestException(MessageConstant.CATEGORY.ITEMCATEGORYNOTFOUND));
        categoryEntity.setDelete_at(Instant.now());
        categoryEntity.setIsDeleted(true);
        categoryRepository.save(categoryEntity);
    }
}
