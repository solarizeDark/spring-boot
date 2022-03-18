package ru.fedusiv.validation.validators;

import ru.fedusiv.validation.annotations.ValidInitials;
import ru.fedusiv.validation.annotations.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InitialsValidator implements ConstraintValidator<ValidInitials, String> {

    @Override
    public boolean isValid(String initial, ConstraintValidatorContext constraintValidatorContext) {
        return initial.matches("[A-Z][a-z]+") && initial.length() > 3;
    }

}
