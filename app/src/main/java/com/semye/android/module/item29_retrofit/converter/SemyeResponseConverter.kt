package com.semye.android.module.item29_retrofit.converter

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter

class SemyeResponseConverter<T>(var gson: Gson) : Converter<ResponseBody, SemyeResponse<T>> {

    override fun convert(value: ResponseBody): SemyeResponse<T> {
        try {
            return gson.fromJson(value.string(), SemyeResponse::class.java) as SemyeResponse<T>
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return SemyeResponse()
    }
}