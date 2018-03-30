package com.example.cobe.articlesapp.common;

/**
 * Created by cobe on 26/03/2018.
 */

public class ValidationUtils {

    public static boolean isEmpty(String string) {
        return !(string != null && string.isEmpty() && string.trim().isEmpty());
    }
}
