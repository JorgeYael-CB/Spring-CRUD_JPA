package com.yael.curso.springboot.app.springboot_crud.entities;

import com.yael.curso.springboot.app.springboot_crud.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity // jpa - hibernate maneja la tabla en la base de datos automaticamente
@Table(name="products") // especificas el nombre de la tabla
public class Product {

    @Id // indica que sera una clave primaria
    @GeneratedValue( // indica que esto sera generado por la base de datos de forma automatica
        strategy=GenerationType.IDENTITY // jpa - indica que sera de forma incremental
    )
    private Long id;

    @IsRequired
    @Size(min=3, max=20)
    private String name;

    @Min(value=500, message="debe ser mayor de 500 dolares")
    @NotNull(message="{NotNull.product.price}")
    private Integer price;

    @IsRequired // error personalizado
    @Size(min=10, max=20)
    private String description;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
