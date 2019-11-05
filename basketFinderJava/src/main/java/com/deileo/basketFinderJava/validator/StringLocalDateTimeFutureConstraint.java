package com.deileo.basketFinderJava.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringLocalDateTimeFutureValidator.class)
public @interface StringLocalDateTimeFutureConstraint {

    String message() default "Only future dates and times are allowed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}