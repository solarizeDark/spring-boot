package ru.fedusiv.validation.annotations;

import ru.fedusiv.validation.validators.InitialsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = InitialsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInitials {
    String message() default "invalid initials";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
