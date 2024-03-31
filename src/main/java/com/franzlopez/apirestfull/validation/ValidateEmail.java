package com.franzlopez.apirestfull.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidateEmail {
    public String message() default "Email es inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
