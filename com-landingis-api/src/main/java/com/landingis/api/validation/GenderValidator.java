package com.landingis.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class GenderValidator implements ConstraintValidator<GenderConstraint, Integer> {
    private boolean allowNull;
    private static final List<Integer> VALID_GENDERS = Arrays.asList(1, 2, 3);

    @Override
    public void initialize(GenderConstraint constraintAnnotation) {
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return allowNull;
        }
        return VALID_GENDERS.contains(value);
    }
}
