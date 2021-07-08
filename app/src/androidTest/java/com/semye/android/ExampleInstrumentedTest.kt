package com.semye.android

import android.os.Environment
import android.util.Log
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.semye.android", appContext.packageName)
    }

    @Test
    fun readLog() {
        Log.d(TAG, Environment.getExternalStorageDirectory().absolutePath)
    }

    @Test
    fun printLog() {
        Log.d(TAG, Environment.getDownloadCacheDirectory().absolutePath)
    }

    @Test
    fun writeFile() {
    }

    companion object {
        const val TAG = "ExampleInstrumentedTest"
    }
}