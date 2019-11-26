package com.deileo.basketFinderJava.validator;

import com.deileo.basketFinderJava.payload.EventDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventDateRangeValidatorTest {

    @MockBean
    private ConstraintValidatorContext context;

    @Autowired
    private EventDateRangeValidator validator;

    @Test
    public void testShouldReturnTrueIfEndTimeDoesNotExist() {
        assertTrue(validator.isValid(new EventDto(), context));
    }

    @Test
    public void testShouldReturnTrueIfEndTimeIsLaterThanStartTime() {
        EventDto eventDto = new EventDto();
        eventDto.setStartTime("2019-01-01T10:00:00");
        eventDto.setEndTime("2019-01-01T12:00:00");

        assertTrue(validator.isValid(eventDto, context));
    }

    @Test
    public void testShouldReturnFalseIfEndTimeIsBeforeStartTime() {
        EventDto eventDto = new EventDto();
        eventDto.setStartTime("2019-01-01T12:00:00");
        eventDto.setEndTime("2019-01-01T10:00:00");

        assertFalse(validator.isValid(eventDto, context));
    }

    @Test(expected = DateTimeParseException.class)
    public void testShouldReturnFalseIfTimeFormatIsIncorrect() {
        EventDto eventDto = new EventDto();
        eventDto.setStartTime("2019/01/01");
        eventDto.setEndTime("2019/01/01");

        assertFalse(validator.isValid(eventDto, context));
    }
}
