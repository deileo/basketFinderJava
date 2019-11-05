package com.deileo.basketFinderJava.validator;

import com.deileo.basketFinderJava.payload.EventDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EventDateRangeValidator implements ConstraintValidator<EventDateRangeConstraint, EventDto> {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public boolean isValid(EventDto eventDto, ConstraintValidatorContext context) {

        try {
            if (eventDto.getEndTime() == null) {
                return true;
            }

            LocalDateTime startTimeObject = LocalDateTime.parse(eventDto.getStartTime(), DateTimeFormatter.ofPattern(dateFormat));
            LocalDateTime endTimeObject = LocalDateTime.parse(eventDto.getEndTime(), DateTimeFormatter.ofPattern(dateFormat));

            if (!endTimeObject.isAfter(startTimeObject)) {
                context.buildConstraintViolationWithTemplate("End time date must after start time!")
                    .addPropertyNode("endTime")
                    .addConstraintViolation();

                return false;
            }
        } catch (Exception ignore) {
            System.out.println(ignore.getMessage());

            return false;
        }

        return true;
    }
}
