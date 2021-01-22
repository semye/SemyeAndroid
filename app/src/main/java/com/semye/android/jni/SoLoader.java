package com.semye.android.jni;

/**
 * Created by yesheng on 2020/11/26
 */
public class SoLoader {


    static {
        System.loadLibrary("semye");
    }


    public native String getStringFromNative();

    public native int getIntFromNative();

    public native void createFile(String fileName);

    public native int[] bubbleSort(int[] array);
}
