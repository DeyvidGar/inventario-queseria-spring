package com.company.inventory.service;

import com.company.inventory.models.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product, long category_id);
    Product findProductById(long id);
    List<Product> findProductsByName(String name);
}
