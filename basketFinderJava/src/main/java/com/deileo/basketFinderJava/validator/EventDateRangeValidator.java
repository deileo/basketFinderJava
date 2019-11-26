package com.deileo.basketFinderJava.validator;

import com.deileo.basketFinderJava.payload.EventDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EventDateRangeValidator implements ConstraintValidator<EventDateRangeConstraint, EventDto> {

    public boolean isValid(EventDto eventDto, ConstraintValidatorContext context) {

        if (eventDto.getEndTime() == null) {
            return true;
        }

        LocalDateTime startTimeObject = LocalDateTime.parse(eventDto.getStartTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime endTimeObject = LocalDateTime.parse(eventDto.getEndTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        if (!endTimeObject.isAfter(startTimeObject)) {
            context.buildConstraintViolationWithTemplate("End time date must after start time!")
                .addPropertyNode("endTime")
                .addConstraintViolation();

            return false;
        }

        return true;
    }
}
