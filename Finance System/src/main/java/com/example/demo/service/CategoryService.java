package com.example.demo.service;

import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.models.Category;
import com.example.demo.models.User;
import com.example.demo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    public void createCategory(@RequestBody CreateCategoryDTO createCategoryDTO) {
        User currentUser=authenticationService.getLoggedUser();
        Category category = new Category();
        category.setUser(currentUser);
        category.setName(createCategoryDTO.name());
        categoryRepository.save(category);
    }

    public List<Category> findByUser(){
        User currentUser=authenticationService.getLoggedUser();
        return categoryRepository.findAvailableCategoriesForUser(currentUser.getId());
    }
}
