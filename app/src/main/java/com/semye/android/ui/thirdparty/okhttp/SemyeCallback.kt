package com.semye.android.ui.thirdparty.okhttp

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

abstract class SemyeCallback : Callback {

    override fun onFailure(call: Call, e: IOException) {
        onFailure(e)
    }

    @Throws(IOException::class)
    override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful && response.body() != null) {
            onSuccess(response.body())
        } else {
            onFailure(Exception(response.message()))
        }
    }

    abstract fun onSuccess(response: ResponseBody?)

    abstract fun onFailure(t: Throwable?)
}