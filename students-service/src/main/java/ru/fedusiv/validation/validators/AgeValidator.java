package ru.fedusiv.validation.validators;

import ru.fedusiv.validation.annotations.ValidAge;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, Integer> {

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        return age > 5 && age < 200;
    }

}
