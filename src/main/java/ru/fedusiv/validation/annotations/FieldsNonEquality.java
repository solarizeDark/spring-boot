package ru.fedusiv.validation.annotations;

import ru.fedusiv.validation.validators.FieldsNonEqualityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsNonEqualityValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsNonEquality {

    String message() default "Username and password are equal";

    String field1();
    String field2();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
