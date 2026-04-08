package com.example.demo.controller;

import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.dto.ResponseCategoryDTO;
import com.example.demo.models.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ResponseCategoryDTO>> getMyCategories(){
        List<Category> myCategorys=categoryService.findByUser();
        List<ResponseCategoryDTO> dto=myCategorys.stream()
                .map(category -> new ResponseCategoryDTO(
                        category.getName()
                ))
                .toList();
        return ResponseEntity.ok(dto);
    }
}
