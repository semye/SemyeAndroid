package com.semye.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yesheng on 2018/7/4.
 * 正则表达式工具类
 */
public class RegexUtils {

    /**
     * 判断是否是手机号
     *
     * @param mobile 字符串
     * @return
     */
    public static boolean isMobilePhone(String mobile) {
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.find();
    }

}
