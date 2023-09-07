package com.company.inventory.service;

import com.company.inventory.models.Product;

public interface ProductService {
    Product saveProduct(Product product, long category_id);
    Product findProductById(long id);
}
