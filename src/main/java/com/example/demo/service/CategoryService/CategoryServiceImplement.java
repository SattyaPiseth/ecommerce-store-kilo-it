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

import java.util.UUID;

@Service

public class CategoryServiceImplement implements CategoryService {
private final CategoryRepository categoryRepository;

    public CategoryServiceImplement(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public StructureRS getCategory(BaseListingRQ baseListingRQ) {
        Page<CategoryEntityInfo>categoryEntityInfos=categoryRepository.findByNameContains(baseListingRQ.getQuery(),baseListingRQ.getPageable() );
        categoryEntityInfos.getContent();
       return new StructureRS(categoryEntityInfos);
    }

    @Override
    @Transactional
    public void addCategory(CategoryRQ categoryRQ) {
        boolean isExisted = categoryRepository.existsByNameStartsWith(categoryRQ.getName());
        if (isExisted){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"name existed");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setUuid(UUID.randomUUID().toString());
        categoryEntity.setName(categoryRQ.getName());
        categoryEntity.setDescription(categoryRQ.getDescription());
        categoryRepository.save(categoryEntity);
    }

    @Override
    @Transactional
    public void updateCategory(Integer id, CategoryRQ categoryRQ) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category id not found"));
        categoryEntity.setName(categoryRQ.getName());
        categoryEntity.setDescription(categoryRQ.getDescription());
        categoryRepository.save(categoryEntity);
    }

    @Override
    @Transactional
    public void deleteCategory(Integer id) {
        CategoryEntity categoryEntity= categoryRepository.findById(id)
                .orElseThrow(()-> new BadRequestException(MessageConstant.ITEMNOTFOUND));
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category id not found"));

        categoryRepository.deleteById(categoryEntity.getId());

    }
}
