package com.springapp.mvc.util;

/**
 * Created by Y. Vovk on 21.09.15.
 */
public interface IConverter<DOMAIN, T> {

    DOMAIN convert(T obj);
}
