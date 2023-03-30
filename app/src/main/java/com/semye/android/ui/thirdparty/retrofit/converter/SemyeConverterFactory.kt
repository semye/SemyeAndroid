package com.semye.android.ui.thirdparty.retrofit.converter

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by yesheng on 2018/10/25.
 * 自定义Converter
 */
class SemyeConverterFactory(var gson: Gson) : Converter.Factory() {


    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        return SemyeResponseConverter<Type>(gson) as Converter<ResponseBody, *>
    }

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return SemyeRequestConverter<Type>()
    }

    companion object {
        fun create(gson: Gson): SemyeConverterFactory {
            return SemyeConverterFactory(gson)
        }
    }
}