package com.company.inventory.models.response;

import com.company.inventory.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    //agregamos esto a la respuesta
    List<Product> products;
}
