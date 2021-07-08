package com.semye.android

import com.semye.android.utils.MD5Utils.encode
import com.semye.android.utils.MD5Utils.encode16
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    @Throws(Exception::class)
    fun md5() {
        Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", encode("123456"))
        Assert.assertEquals("49ba59abbe56e057", encode16("123456"))
    }
}