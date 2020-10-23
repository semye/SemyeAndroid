package com.semye.android;

import android.content.Context;
import android.os.Environment;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    public static final String TAG = "ExampleInstrumentedTest";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.semye.android", appContext.getPackageName());
    }

    @Test
    public void readLog() {
        Log.d(TAG, Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    @Test
    public void printLog() {
        Log.d(TAG, Environment.getDownloadCacheDirectory().getAbsolutePath());
    }

    @Test
    public void writeFile() {

    }
}
