package com.springapp.mvc.validation.impl;

import com.springapp.mvc.domain.Film;
import com.springapp.mvc.exception.ValidationException;
import com.springapp.mvc.validation.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Y. Vovk on 23.09.15.
 */
@Component
public class FilmValidator implements IValidator<Film> {

    @Autowired
    private Validator validator;

    public void validate(Film obj) {
        Set<ConstraintViolation<Film>> constraintViolations = validator.validate(obj);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<Film> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage());
            }
            throw new ValidationException(errors.toString());
        }
    }

}
