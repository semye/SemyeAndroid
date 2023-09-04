package com.semye.android.ui.item29_retrofit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.semye.android.R
import com.semye.android.ui.item28_okhttp.OkHttpUtils
import com.semye.android.ui.item29_retrofit.calladapter.SemyeCallAdapterFactory
import com.semye.android.ui.item29_retrofit.converter.SemyeConverterFactory
import com.semye.android.ui.item29_retrofit.converter.SemyeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * retrofit2的用法
 */
class Retrofit2MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_retrofit)
        findViewById<View>(R.id.root).setOnClickListener {
            doGet()
        }
    }

    private fun doGet() {
        try {
            val retrofit = Retrofit.Builder()
                .client(OkHttpUtils.defaultOkHttpClient)
                .baseUrl("https://www.baidu.com")
                .addCallAdapterFactory(SemyeCallAdapterFactory.create())
                .addConverterFactory(SemyeConverterFactory.create(Gson()))
                .build()
            val apiService = retrofit.create(ApiService::class.java)
            val semyeCall = apiService.getService6("com", "222")
            println(semyeCall.toString())
            semyeCall?.call?.enqueue(object : Callback<SemyeResponse<String>> {
                override fun onResponse(call: Call<SemyeResponse<String>>, response: Response<SemyeResponse<String>>) {
                    println("====>" + response.body()?.message)
                }

                override fun onFailure(call: Call<SemyeResponse<String>>, t: Throwable) {
                    println("====>" + t.message)
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}