package com.yael.curso.springboot.app.springboot_crud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;



@Retention(RetentionPolicy.RUNTIME) // tiempo de ejecucion
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR}) // donde se podra usar el decorador
@Constraint( validatedBy=RequiredValidation.class ) // la clase que hace la validacion
public @interface IsRequired {


    //Mensaje por defecto del decorador
    // String message() default "{jakarta.validation.constraints.NotBlank.message}";
    String message() default "Es requerido usando anotaciones";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
