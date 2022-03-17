package ru.fedusiv.validation.validators;

import org.springframework.beans.BeanWrapperImpl;
import ru.fedusiv.validation.annotations.UsernamePasswordNonEquality;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernamePasswordNonEqualityValidator implements ConstraintValidator<UsernamePasswordNonEquality, Object> {

    private String usernameField;
    private String passwordField;

    @Override
    public void initialize(UsernamePasswordNonEquality constraintAnnotation) {
        this.usernameField = constraintAnnotation.username();
        this.passwordField = constraintAnnotation.password();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object name = new BeanWrapperImpl(value).getPropertyValue(usernameField);
        Object password = new BeanWrapperImpl(value).getPropertyValue(passwordField);

        return name != null && !name.equals(password);
    }

}
