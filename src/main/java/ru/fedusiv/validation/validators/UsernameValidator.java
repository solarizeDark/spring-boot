package ru.fedusiv.validation.validators;

import ru.fedusiv.validation.annotations.ValidUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username.length() > 2 && username.matches("\\D*")
                && !username.matches(".*[~!@#'\"$%^&*()<>?,./{}\\[\\]+-].*");
    }

}
