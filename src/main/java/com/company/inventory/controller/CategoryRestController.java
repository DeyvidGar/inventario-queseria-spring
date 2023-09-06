package com.company.inventory.controller;

import com.company.inventory.models.Category;
import com.company.inventory.models.response.CategoryResponseRest;
import com.company.inventory.service.CategoryService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
            categoryResponseRest.setMetadata(HttpStatus.OK, "Categories found.");

            return ResponseEntity.ok(categoryResponseRest);
        } catch (Exception e) {
            categoryResponseRest.setMetadata(HttpStatus.INTERNAL_SERVER_ERROR, "Error to get Categories.");
            e.printStackTrace();
            return new ResponseEntity<>(categoryResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseRest> findByIdCategory(@PathVariable long id) {
        CategoryResponseRest categoryResponseRest = new CategoryResponseRest();

        try {
            Category categories = this.categoryService.findCategoryById(id);

            if (categories == null) throw new Exception("Not found category");

            categoryResponseRest.getCategoryResponse().setCategories(Arrays.asList(categories));
            categoryResponseRest.setMetadata(HttpStatus.OK, "Category located.");

            return ResponseEntity.ok(categoryResponseRest);
        } catch (Exception e) {
            categoryResponseRest.setMetadata(HttpStatus.NO_CONTENT, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(categoryResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category) {
        CategoryResponseRest categoryResponseRest = new CategoryResponseRest();

        try {
            Category categories = this.categoryService.saveCategory(category);

            categoryResponseRest.getCategoryResponse().setCategories(Arrays.asList(categories));
            categoryResponseRest.setMetadata(HttpStatus.OK, "Category saved.");

            return ResponseEntity.ok(categoryResponseRest);
        } catch (Exception e) {
            categoryResponseRest.setMetadata(HttpStatus.BAD_REQUEST, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(categoryResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
