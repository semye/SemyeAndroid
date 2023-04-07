package com.semye.android.utils

import org.junit.Test
import java.util.Calendar

class DateTimeUtilsTest {

    @Test
    fun testToDate() {
        val date = DateTimeUtils.timestampToDate(System.currentTimeMillis())
        //Sat Apr 08 00:38:09 CST 2023
        println(date)
        println(date.toInstant())

        val year = DateTimeUtils.timestampGetYear(System.currentTimeMillis())
        println(Calendar.getInstance().time)
        println(year)

        val timestampFormat1 = DateTimeUtils.timestampFormat1(System.currentTimeMillis())
        println(timestampFormat1)

        val date1 = DateTimeUtils.timestampParse("2023年4月8日")
        println(date1)

        val timestampFormat2 = DateTimeUtils.timestampFormat2(System.currentTimeMillis())
        println(timestampFormat2)

        val timestampFormat = DateTimeUtils.timestampFormat(1680936869000)
        println(timestampFormat)
    }
}