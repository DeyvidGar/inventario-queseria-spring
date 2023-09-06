package com.company.inventory.service;

import com.company.inventory.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findCategoryById(long id);
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(long id);

}
