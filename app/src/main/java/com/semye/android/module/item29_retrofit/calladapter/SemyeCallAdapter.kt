package com.semye.android.module.item29_retrofit.calladapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Created by yesheng on 2018/9/18.
 * 自定义CallAdapter
 */
class SemyeCallAdapter<R>(private val responseType: Type) : CallAdapter<R, SemyeCall<R>> {


    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): SemyeCall<R> {
        return SemyeCall<R>(call)
    }


}