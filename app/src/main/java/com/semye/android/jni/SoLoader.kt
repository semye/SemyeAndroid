package com.semye.android.jni

/**
 * Created by yesheng on 2020/11/26
 */
class SoLoader {
    companion object {
        init {
            System.loadLibrary("semye")
        }
    }

    val stringFromNative: String?
        external get
    val intFromNative: Int
        external get

    external fun createFile(fileName: String?)
    external fun bubbleSort(array: IntArray?): IntArray?
}