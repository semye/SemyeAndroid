package com.semye.android.thirdparty.retrofit.calladapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by yesheng on 2018/9/18.
 * 自定义CallAdapter
 */
class SemyeCallAdapterFactory : CallAdapter.Factory() {


    companion object {
        fun create(): CallAdapter.Factory {
            return SemyeCallAdapterFactory()
        }
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)//获取泛型
        if (rawType != SemyeCall::class.java) {
            return null
        }
        if (returnType !is ParameterizedType) {
            return null
        }
        val type = getParameterUpperBound(0, returnType)
        return SemyeCallAdapter<Type>(type)
    }
}