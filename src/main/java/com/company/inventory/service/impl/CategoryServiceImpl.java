package com.company.inventory.service.impl;

import com.company.inventory.models.Category;
import com.company.inventory.repository.CategoryRepository;
import com.company.inventory.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl( CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        this.categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    @Transactional(readOnly = true)
    public Category findCategoryById(long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }
}
