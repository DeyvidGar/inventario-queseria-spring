package com.company.inventory.controller;

import com.company.inventory.models.Product;
import com.company.inventory.models.response.CategoryResponseRest;
import com.company.inventory.models.response.ProductResponseRest;
import com.company.inventory.service.ProductService;
import com.company.inventory.utils.UtilImageCompress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {
    private ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    /**
     *
     * @param multipartFile
     * @param name
     * @param price
     * @param account
     * @param category_id
     * @return
     * @throws IOException
     */
    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponseRest> saveProduct(
            @RequestParam MultipartFile multipartFile,
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam double account,
            @RequestParam long category_id
            ) throws IOException {
        System.out.println("name = " + name);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setAccount(account);
        product.setPicture(UtilImageCompress.compressZLib(multipartFile.getBytes()));

        product = this.productService.saveProduct(product, category_id);

        ProductResponseRest productResponseRest = new ProductResponseRest();
        productResponseRest.setMetadata(HttpStatus.OK, "Product created");
        productResponseRest.getProductResponse().setProducts(Arrays.asList(product));
        return ResponseEntity.ok(productResponseRest);
    }

}
