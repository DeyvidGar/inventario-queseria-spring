package com.company.inventory.service.impl;

import com.company.inventory.models.Category;
import com.company.inventory.models.Product;
import com.company.inventory.repository.ProductRepository;
import com.company.inventory.service.CategoryService;
import com.company.inventory.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product saveProduct(Product product, long category_id) {
        try {
            Category category = this.categoryService.findCategoryById(category_id);
            if(category == null) throw new Exception("Incorrect category");

            product.setCategory(category);
            return this.productRepository.save(product);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Product findProductById(long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return this.productRepository.findByNameContaining(name);
    }
}
