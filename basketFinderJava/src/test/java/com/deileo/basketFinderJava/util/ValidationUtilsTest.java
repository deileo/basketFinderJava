package com.deileo.basketFinderJava.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidationUtilsTest {

    @MockBean
    private BindingResult bindingResult;

    @Autowired
    private ValidationUtils validationUtils;

    @Test
    public void testShouldReturnMapOfErrors() {
        List<FieldError> errors = new ArrayList<>();
        FieldError error = new FieldError("Event", "Name", "Cannot be blank");
        errors.add(error);

        when(bindingResult.getFieldErrors()).thenReturn(errors);

        Map<String, List<String>> errorsMap = validationUtils.getErrorsMap(bindingResult);

        errorsMap.forEach((k, v) -> {
            assertEquals("Name", k);
            assertEquals("Cannot be blank", v.get(0));
        });

    }

    @Test
    public void testShouldReturnEmptyMapIfNoErrorsFound() {
        when(bindingResult.getFieldErrors()).thenReturn(new ArrayList<>());

        assertEquals(0, validationUtils.getErrorsMap(bindingResult).size());
    }
}
