package com.example.demo.controller;

import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @PostMapping()
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO){
        categoryService.createCategory(createCategoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
