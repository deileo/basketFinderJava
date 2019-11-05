package com.deileo.basketFinderJava.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ValidationUtils {

    public Map<String, List<String>> getErrorsMap(BindingResult bindingResult) {
        Map<String, List<String>> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            List<String> errorList = new ArrayList<>();
            errorList.add(fieldError.getDefaultMessage());
            if (!errors.containsKey(fieldError.getField())) {
                errors.put(fieldError.getField(), errorList);
            }
        });

        return errors;
    }
}
