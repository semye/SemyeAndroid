package com.semye.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by semye on 2018/4/28.
 */
public class StringUtils {

    public static final String MOBILE_REGEX = "^1\\d{10}$";

    public static boolean isPhone(String mobiles) {
        try {
            Pattern p = Pattern.compile(MOBILE_REGEX);
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }


}
