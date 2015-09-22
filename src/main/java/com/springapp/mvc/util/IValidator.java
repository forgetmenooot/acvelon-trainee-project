package com.springapp.mvc.util;

/**
 * Created by Y. Vovk on 21.09.15.
 */
public interface IValidator<DOMAIN, T> {

    DOMAIN validate(T obj);
}
