package com.deileo.basketFinderJava.validator;

import com.deileo.basketFinderJava.payload.EventDto;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventDateRangeValidator implements ConstraintValidator<EventDateRangeConstraint, EventDto> {

    private String startTime;

    private String endTime;

    public void initialize(EventDateRangeConstraint constraintAnnotation) {
        startTime = constraintAnnotation.startTime();
        endTime = constraintAnnotation.endTime();
    }

    public boolean isValid(EventDto eventDto, ConstraintValidatorContext context) {

        try {
            LocalDateTime startTimeObject = LocalDateTime.parse(
                    BeanUtils.getProperty(eventDto, startTime), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );
            LocalDateTime endTimeObject = LocalDateTime.parse(
                    BeanUtils.getProperty(eventDto, endTime), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );

            context.buildConstraintViolationWithTemplate("End time date must after start time!")
                    .addPropertyNode("endTime")
                    .addConstraintViolation();

            return endTimeObject.isAfter(startTimeObject);
        } catch (Exception ignore) { }

        return false;
    }
}
