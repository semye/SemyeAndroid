#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <android/bitmap.h>
#include <stdio.h>
#include <errno.h>
#include "semye.h"

//
// Created by semye on 2020/11/26.
//

#define FALSE 0
#define TRUE 1

JNIEXPORT jint

JNICALL
Java_com_semye_android_jni_SoLoader_getIntFromNative(JNIEnv *env, jobject thiz) {
    return 1;
}


JNIEXPORT jstring

JNICALL
Java_com_semye_android_jni_SoLoader_getStringFromNative(JNIEnv *env, jobject thiz) {
    char *a = "hello world!";
    jstring retString = (*env)->NewStringUTF(env, a);
    __android_log_write(ANDROID_LOG_VERBOSE, "Semye", "写入完成");
    return retString;
}

JNIEXPORT void JNICALL
Java_com_semye_android_jni_SoLoader_createFile(JNIEnv *env, jobject thiz, jstring file_name) {
    const char *text = (*env)->GetStringUTFChars(env, file_name, NULL);
    __android_log_write(ANDROID_LOG_VERBOSE, "Semye", text);
    FILE *file = fopen(text, "w");
    if (file != NULL) {
        fputs("hello world", file);
        fclose(file);
        __android_log_write(ANDROID_LOG_VERBOSE, "Semye", "写入完成");
    } else {
        __android_log_write(ANDROID_LOG_VERBOSE, "Semye", "创建文件失败");
        __android_log_write(ANDROID_LOG_VERBOSE, "Semye", strerror(errno));
    }

}

JNIEXPORT jintArray JNICALL
Java_com_semye_android_jni_SoLoader_bubbleSort(JNIEnv *env, jobject thiz, jintArray array) {
    // implement bubbleSort()
    jint *arrays = (*env)->GetIntArrayElements(env, array, NULL);
    jint length = (*env)->GetArrayLength(env, array);
    for (int i = 0; i < length - 1; ++i) {
        for (int j = 0; j < length - 1 - i; ++j) {
            if (arrays[j] > arrays[j + 1]) {
                int temp = arrays[j];
                arrays[j] = arrays[j + 1];
                arrays[j + 1] = temp;
            }
        }
    }
    // 创建数组
    jintArray array1 = (*env)->NewIntArray(env, length);
    // 把排序后的数组赋值给创建的数组
    (*env)->SetIntArrayRegion(env, array1, 0, length, arrays);
    return array1;
}