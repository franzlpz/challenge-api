package com.franzlopez.apirestfull.validation;

import com.franzlopez.apirestfull.utils.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidatePassword {
    public String message() default "Password es inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
