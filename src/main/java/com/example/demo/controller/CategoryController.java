package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.StructureRS;
import com.example.demo.constant.MessageConstant;
import com.example.demo.model.request.Category.CategoryRQ;
import com.example.demo.service.CategoryService.CategoryService;
import com.example.demo.service.CategoryService.CategoryServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

@ResponseStatus(HttpStatus.OK)
@GetMapping
public StructureRS getCategory(BaseListingRQ baseListingRQ) {
    return new StructureRS(categoryService.getCategory(baseListingRQ));
}

@ResponseStatus(HttpStatus.CREATED)
@PostMapping
    public StructureRS addCategory(@Validated @RequestBody CategoryRQ categoryRQ){
        categoryService.addCategory(categoryRQ);
        return new StructureRS(MessageConstant.SUCCESSFULLY);
    }
@ResponseStatus(HttpStatus.OK)
@PutMapping("/{uuid}")
    public StructureRS updateCategory(@Validated @PathVariable String uuid ,@RequestBody CategoryRQ categoryRQ  ){
    categoryService.updateCategory(uuid,categoryRQ);
    return new StructureRS(MessageConstant.SUCCESSFULLY);
}
@ResponseStatus(HttpStatus.OK)
@DeleteMapping("/{uuid}")
    public StructureRS deleteCategory(@Validated @PathVariable String uuid ){
        categoryService.deleteCategory(uuid);
        return new StructureRS(MessageConstant.SUCCESSFULLY);
}
}
