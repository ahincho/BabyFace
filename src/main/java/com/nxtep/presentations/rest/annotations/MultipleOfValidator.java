package com.nxtep.presentations.rest.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultipleOfValidator implements ConstraintValidator<MultipleOf, Integer> {
    private int multiple;
    @Override
    public void initialize(MultipleOf constraintAnnotation) {
        this.multiple = constraintAnnotation.value();
    }
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value % multiple == 0;
    }
}
