package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.BaseListingRQ;
import com.example.demo.base.StructureRS;
import com.example.demo.model.request.Category.CategoryRQ;
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
    private final CategoryServiceImplement categoryService;
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
@ResponseStatus(HttpStatus.FOUND)
@GetMapping
public StructureRS getCategory(BaseListingRQ baseListingRQ) {
    return new StructureRS(categoryService.getCategory(baseListingRQ));
}

@ResponseStatus(HttpStatus.CREATED)
@PostMapping
    public StructureRS addCategory(@Validated @RequestBody CategoryRQ categoryRQ){
        categoryService.addCategory(categoryRQ);
        return new StructureRS(HttpStatus.CREATED,"created");
    }
@ResponseStatus(HttpStatus.ACCEPTED)
@PutMapping("/{id}")
    public StructureRS updateCategory(@Validated @PathVariable Integer id ,@RequestBody CategoryRQ categoryRQ  ){
    categoryService.updateCategory(id,categoryRQ);
    return new StructureRS(HttpStatus.ACCEPTED,"updated");
}
@ResponseStatus(HttpStatus.ACCEPTED)
@DeleteMapping("/{id}")
    public StructureRS deleteCategory(@Validated @PathVariable Integer id ){
        categoryService.deleteCategory(id);
        return new StructureRS(HttpStatus.ACCEPTED,"deleted");
}
}
