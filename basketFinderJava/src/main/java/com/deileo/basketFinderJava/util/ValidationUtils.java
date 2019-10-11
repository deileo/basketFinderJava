package com.deileo.basketFinderJava.util;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidationUtils {

    public Map<String, List<String>> getErrorsMap(BindingResult bindingResult) {
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            List<String> errorList = new ArrayList<>();
            errorList.add(error.getDefaultMessage());
            if (!errors.containsKey(error.getField())) {
                errors.put(error.getField(), errorList);
            }
        }

        return errors;
    }
}
