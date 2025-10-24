package org.mustapha.utilis;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;

public class InputValidation {
    public static Map<String, String> getValidationErrors(BindingResult result){
        Map<String, String>errors = new HashMap<>();
        for (var error: result.getAllErrors()){
            var filedError = (FieldError) error;
            errors.put(filedError.getField(), filedError.getDefaultMessage());
        }
        return  errors;
    }
}
