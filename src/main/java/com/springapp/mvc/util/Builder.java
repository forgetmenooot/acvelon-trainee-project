package com.springapp.mvc.util;

/**
 * Created by Y. Vovk on 21.09.15.
 */
public class Builder {

    private StringBuilder strBuilder = new StringBuilder();

    public void append(String str) {
        strBuilder.append(str);
    }

    public String build() {
        return strBuilder.toString();
    }

}
