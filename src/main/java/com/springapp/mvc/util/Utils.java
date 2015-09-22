package com.springapp.mvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Y. Vovk on 21.09.15.
 */
public class Utils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidNumberRange(Integer number, Integer from, Integer to) {
        return number >= from && number <= to;
    }

    public static boolean isEmptyString(String str) {
        return str.trim().isEmpty();
    }

    public static boolean isValidDate(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            dateFormat.parse(str);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

}
