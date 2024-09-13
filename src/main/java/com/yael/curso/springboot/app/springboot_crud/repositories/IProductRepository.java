package com.yael.curso.springboot.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.yael.curso.springboot.app.springboot_crud.entities.Product;



// CrudRepository es una interfaz generica de JPA que ofrece metodos de un CRUD automaticamente
public interface IProductRepository extends CrudRepository<Product, Long> { // indica el DTO y la llave primaria

}
