package com.yael.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.curso.springboot.app.springboot_crud.entities.Product;
import com.yael.curso.springboot.app.springboot_crud.repositories.IProductRepository;



//NOTA: transactional de spring
//inicia y finaliza transacciones de manera automatica

@Transactional // todos sus metodos son transactional hasta que se especifique que uno especifique que no.
@Service // indica que reprenta logica de negocio
public class ProductServiceImpl implements IProductService {

    @Autowired //DI
    private IProductRepository productRepository;


    @Transactional(readOnly=true)
    @Override
    public List<Product> findAll() {
        return (List<Product>)productRepository.findAll();
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> remove(Long id) {
        Optional<Product> productDb = findById(id);

        productDb.ifPresent( prod -> {
            productRepository.delete(prod);
        });

        return productDb;
    }

}
