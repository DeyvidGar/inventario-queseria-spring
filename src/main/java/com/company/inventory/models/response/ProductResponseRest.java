package com.company.inventory.models.response;

import com.company.inventory.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponseRest extends ResponseRest {
    //agregamos esto a la respuesta
    ProductResponse productResponse = new ProductResponse();
}
