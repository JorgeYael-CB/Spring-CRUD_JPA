package com.yael.curso.springboot.app.springboot_crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.curso.springboot.app.springboot_crud.ProductValidation;
import com.yael.curso.springboot.app.springboot_crud.entities.Product;
import com.yael.curso.springboot.app.springboot_crud.services.IProductService;

import jakarta.validation.Valid;






@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductValidation validation;


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
    public ResponseEntity<?> create( @Valid @RequestBody Product product,
        BindingResult result )
    {
        // validation.validate(product, result); // validaciones personalizadas mediante clases

        if( result.hasFieldErrors() ){
            return validation(result);
        }

        Product productNew = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( @Valid @RequestBody Product product,
        BindingResult result, @PathVariable Long id )
    {
        // validation.validate(product, result); // validaciones personalizadas mediante clases

        if( result.hasFieldErrors() ){
            return validation(result);
        }

        Optional<Product> productUpdated = productService.update(id, product);

        if( productUpdated.isPresent() ){
            return ResponseEntity.status(HttpStatus.CREATED).body(productUpdated.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete( @PathVariable Long id ){
        Optional<Product> prodOptional = productService.remove(id);

        if( !prodOptional.isPresent() ){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(prodOptional.get());
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();

        result.getFieldErrors().forEach( error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
