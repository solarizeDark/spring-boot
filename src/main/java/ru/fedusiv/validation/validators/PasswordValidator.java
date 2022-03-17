package ru.fedusiv.validation.validators;

import ru.fedusiv.validation.annotations.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.length() > 6 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")
                && password.matches(".*[!@#$^&*()].*");
    }

}
