package com.semye.android;

import com.semye.android.utils.MD5Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void md5() throws Exception {
        assertEquals("e10adc3949ba59abbe56e057f20f883e", MD5Utils.encode("123456"));
        assertEquals("49ba59abbe56e057", MD5Utils.encode16("123456"));
    }
}