package com.semye.android.ui.thirdparty.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiCallback<T> : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            onSuccess(responseBody)
        } else {
            onFailure(Throwable(response.message()))
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure(t)
    }

    abstract fun onSuccess(response: T)
    abstract fun onFailure(t: Throwable)
}