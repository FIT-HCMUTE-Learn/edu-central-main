package com.landingis.api.validation.impl;

import com.landingis.api.validation.GenderConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

import static com.landingis.api.constant.GenderConstant.*;

public class GenderConstraintImpl implements ConstraintValidator<GenderConstraint, Integer> {
    private boolean allowNull;
    public static final List<Integer> VALID_GENDERS = Arrays.asList(MALE, FEMALE, OTHER);

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
