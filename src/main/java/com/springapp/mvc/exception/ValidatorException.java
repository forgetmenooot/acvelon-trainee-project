package com.springapp.mvc.exception;

/**
 * Created by Y. Vovk on 21.09.15.
 */
public class ValidatorException extends RuntimeException {

    public ValidatorException() {
        super();
    }

    public ValidatorException(String error) {
        super(error);
    }
}
