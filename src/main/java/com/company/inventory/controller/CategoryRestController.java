package com.company.inventory.controller;

import com.company.inventory.models.Category;
import com.company.inventory.models.response.CategoryResponseRest;
import com.company.inventory.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {

    private CategoryService categoryService;
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
//    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> findAllCategories() {

        CategoryResponseRest categoryResponseRest = new CategoryResponseRest();

        try {
            List<Category> categories = this.categoryService.findAll();

            categoryResponseRest.getCategoryResponse().setCategories( categories );
            categoryResponseRest.setMetadata(HttpStatus.OK, "Category registred.");

            return ResponseEntity.ok(categoryResponseRest);
        } catch (Exception e) {
            categoryResponseRest.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR, "Error to get Categories.");
            e.printStackTrace();
            return new ResponseEntity<>(categoryResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
