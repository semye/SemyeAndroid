package com.semye.android.module.item12_jni

/**
 * Created by yesheng on 2020/11/26
 */
class SoLoader {
    companion object {
        init {
            System.loadLibrary("semye")
        }
    }

    external fun getStringFromNative(): String
    external fun getIntFromNative(): Int
    external fun createFile(fileName: String?)
    external fun bubbleSort(array: IntArray?): IntArray?
}