package com.semye.android.module.item29_retrofit.converter

import okhttp3.RequestBody
import retrofit2.Converter

class SemyeRequestConverter<T> : Converter<T, RequestBody> {
    override fun convert(value: T): RequestBody {
        TODO("Not yet implemented")
    }
}