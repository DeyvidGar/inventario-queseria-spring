package com.company.inventory.repository;

import com.company.inventory.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    //Estos metodos realizan lo mismo uno lo hacemos creando una consulta personalizada y la segunda
    //es aplicando metodos predefinidos de spring que podemos consultar en la documentacion
    @Query("SELECT p FROM Product p WHERE p.name like %?1%")
    List<Product> findByNameLike(String name);

    List<Product> findByNameContaining(String name);

}
