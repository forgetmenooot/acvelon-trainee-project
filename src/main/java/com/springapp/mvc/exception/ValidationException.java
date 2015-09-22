package com.springapp.mvc.exception;

/**
 * Created by Y. Vovk on 21.09.15.
 */
public class ValidationException extends RuntimeException {

    public ValidationException() {
        super();
    }

    public ValidationException(String error) {
        super(error);
    }
}
