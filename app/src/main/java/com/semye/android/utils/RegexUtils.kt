package com.semye.android.utils

import java.util.regex.Pattern

/**
 * Created by yesheng on 2018/7/4.
 * 正则表达式工具类
 */
object RegexUtils {
    /**
     * 判断是否是手机号
     *
     * @param mobile 字符串
     * @return
     */
    fun isMobilePhone(mobile: String?): Boolean {
        val pattern = Pattern.compile("")
        val matcher = pattern.matcher(mobile)
        return matcher.find()
    }
}