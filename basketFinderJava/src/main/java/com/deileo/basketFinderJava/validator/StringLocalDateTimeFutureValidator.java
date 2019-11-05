package com.deileo.basketFinderJava.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringLocalDateTimeFutureValidator implements ConstraintValidator<StringLocalDateTimeFutureConstraint, String> {

    public boolean isValid(String dateTime, ConstraintValidatorContext context) {

        if (dateTime.isEmpty()) {
            return false;
        }

        LocalDateTime dateTimeObject = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return dateTimeObject.isAfter(LocalDateTime.now());
    }

}
