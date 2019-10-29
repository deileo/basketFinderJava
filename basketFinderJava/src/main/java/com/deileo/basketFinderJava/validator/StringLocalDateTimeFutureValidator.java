package com.deileo.basketFinderJava.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringLocalDateTimeFutureValidator implements ConstraintValidator<StringLocalDateTimeFutureConstraint, String> {

    public void initialize(StringLocalDateTimeFutureValidator dateTimeConstraint) {}

    public boolean isValid(String dateTime, ConstraintValidatorContext context) {

        if (dateTime.isEmpty()) {
            return false;
        }

        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return dateTimeObject.isAfter(LocalDateTime.now());
    }

}
