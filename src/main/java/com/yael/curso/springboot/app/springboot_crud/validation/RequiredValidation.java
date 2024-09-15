package com.yael.curso.springboot.app.springboot_crud.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;




// T1 -> la anotacion
// T2 -> el valora a validar
// es la clase que hace la logica par ala validacion
public class RequiredValidation implements ConstraintValidator<IsRequired, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // return value != null && !value.isBlank();
        return StringUtils.hasText(value);
    }

}
