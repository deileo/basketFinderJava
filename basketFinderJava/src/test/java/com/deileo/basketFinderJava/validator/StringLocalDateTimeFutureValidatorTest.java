package com.deileo.basketFinderJava.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StringLocalDateTimeFutureValidatorTest {

    @MockBean
    private ConstraintValidatorContext context;

    @Autowired
    private StringLocalDateTimeFutureValidator validator;

    @Test
    public void testShouldReturnFalseIfDateTimeIsEmpty() {
        assertFalse(validator.isValid("", context));
    }

    @Test
    public void testShouldReturnFalseIfDateIsAfterCurrentDate() {
        String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        assertFalse(validator.isValid(yesterday, context));
    }

    @Test
    public void testShouldReturnTrueIfDateIsInTheFuture() {
        String tomorrow = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        assertTrue(validator.isValid(tomorrow, context));
    }
}
