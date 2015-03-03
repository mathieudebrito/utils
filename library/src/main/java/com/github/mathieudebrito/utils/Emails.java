package com.github.mathieudebrito.utils;

import com.google.common.base.Strings;

import java.util.regex.Pattern;

public class Emails {
    private static Pattern getEmailPattern(){
        return android.util.Patterns.EMAIL_ADDRESS;
    }
    public final static boolean isValid(String text) {
        return !Strings.isNullOrEmpty(text) && getEmailPattern().matcher(text).matches();
    }
}
