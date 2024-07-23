package com.have.it.backend.v1.common.util;

import java.util.regex.Pattern;

public abstract class RegexUtil {

    public static boolean matches(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }
}
