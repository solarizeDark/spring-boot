package ru.fedusiv.validation.validators;

import org.springframework.beans.BeanWrapperImpl;
import ru.fedusiv.validation.annotations.FieldsNonEquality;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsNonEqualityValidator implements ConstraintValidator<FieldsNonEquality, Object> {

    private String field1;
    private String field2;

    @Override
    public void initialize(FieldsNonEquality constraintAnnotation) {
        this.field1 = constraintAnnotation.field1();
        this.field2 = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object name = new BeanWrapperImpl(value).getPropertyValue(field1);
        Object password = new BeanWrapperImpl(value).getPropertyValue(field2);

        return name != null && !name.equals(password);
    }

}
