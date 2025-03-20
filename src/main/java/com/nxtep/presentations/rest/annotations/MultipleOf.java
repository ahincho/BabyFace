package com.nxtep.presentations.rest.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MultipleOfValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleOf {
    int value();
    String message() default "El valor debe ser un múltiplo de {value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
