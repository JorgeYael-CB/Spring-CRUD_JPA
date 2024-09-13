package com.yael.curso.springboot.app.springboot_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.curso.springboot.app.springboot_crud.entities.Product;
import com.yael.curso.springboot.app.springboot_crud.services.IProductService;






@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping
    public List<Product> list(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view( @PathVariable Long id ){
        Optional<Product> prodOptional = productService.findById(id);

        if( !prodOptional.isPresent() ){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(prodOptional);
    }

    @PostMapping
    public ResponseEntity<Product> create( @RequestBody Product product ){
        Product productNew = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update( @PathVariable Long id, @RequestBody Product product ){
        product.setId(id); // si tiene el Id lo actualiza, si no lo tiene lo borra
        Product productUpdated = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete( @PathVariable Long id ){
        Optional<Product> prodOptional = productService.remove(id);

        if( !prodOptional.isPresent() ){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(prodOptional.get());
    }

}
