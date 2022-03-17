package ru.fedusiv.validation.annotations;

import ru.fedusiv.validation.validators.UsernamePasswordNonEqualityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernamePasswordNonEqualityValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernamePasswordNonEquality {

    String message() default "Username and password are equal";

    String password();
    String username();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
