package com.yael.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.yael.curso.springboot.app.springboot_crud.entities.Product;



public interface IProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> remove(Long id);

}
