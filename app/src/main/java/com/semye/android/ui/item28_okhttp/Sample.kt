package com.semye.android.ui.item28_okhttp

import okhttp3.CacheControl
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

/**
 *  Created by yesheng on 2020/8/12
 *
 *  幂等 参考 https://developer.mozilla.org/zh-CN/docs/Glossary/%E5%B9%82%E7%AD%89
 *
 *
 */

/**
 * get 请求
 * 添加参数
 */
fun doGet() {
    try {
        val httpUrl = HttpUrl.parse("https://www.baidu.com")
        val url = httpUrl?.run {
            newBuilder()
                .addQueryParameter("param1", "1")//添加参数
                .addEncodedQueryParameter("param2", "2")//添加参数并对参数进行编码
                .build()
        }?.url().toString()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(
                call: Call,
                e: IOException
            ) {
                log("请求失败")
            }

            @Throws(IOException::class)
            override fun onResponse(
                call: Call,
                response: Response
            ) {
                log("请求成功")
            }
        })
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * x-www-form-urlencoded
 * 表单提交
 */
fun doPost() {
    val formBuilder = FormBody.Builder()
    formBuilder.add("name1", "value1")
    formBuilder.add("name2", "value2")
    val formBody = formBuilder.build()
    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .post(formBody)
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}

/**
 * post 传json
 */
fun doPost2() {
    val jsonObject = JSONObject()
    jsonObject.put("param1", "param1")
    jsonObject.put("param2", "param2")
    val jsonBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())
    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .post(jsonBody)
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}

/**
 * post multipart 传不同的内容
 *
 * 例如 Content-Type: multipart/mixed; boundary=b5fc2b9d-3024-490b-9464-6223af0d28f4
 * 根据里面的boundary做内容分割
 */
fun doPost3() {
    val jsonObject = JSONObject()
    jsonObject.put("param1", "param1")
    jsonObject.put("param2", "param2")
    val jsonBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())

    val formBuilder = FormBody.Builder()
    formBuilder.add("name1", "value1")
    formBuilder.add("name2", "value2")
    val formBody = formBuilder.build()

    val multipartBuilder = MultipartBody.Builder()
    multipartBuilder.addPart(jsonBody)
    multipartBuilder.addPart(formBody)
    val multipartBody = multipartBuilder.build()

    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .post(multipartBody)
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}

/**
 * post
 *
 * form-data 上传文件
 *
 *
 */
fun doPost4(byteArray: ByteArray) {
    val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), byteArray)
    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .post(requestBody)
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}

/**
 * 下载
 */
fun doDownload() {

    val request = Request.Builder()
        .url("")
        .build()
    val call= OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object :Callback{
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        override fun onResponse(call: Call, response: Response) {
          val content=response.body()?.byteStream()
        }
    })
}

/**
 * head请求 只会获取response里的head信息 不会获取content
 */
fun doHead() {
    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .head()
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}

fun doPatch() {
    val requestBody =
        RequestBody.create(MediaType.parse("text/html"), "hahhahaha")
    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .patch(requestBody)
        .addHeader("hello", "world")
        .header("hello1", "yesheng")
        .cacheControl(CacheControl.FORCE_NETWORK)
        .tag("first")
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}

fun doDelete() {
    val requestBody =
        RequestBody.create(MediaType.parse("application/json"), "hahhahaha")
    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .delete(requestBody)
        .addHeader("hello", "world")
        .header("hello1", "yesheng")
        .cacheControl(CacheControl.FORCE_NETWORK)
        .tag("first")
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}

fun doPut() {
    val requestBody =
        RequestBody.create(MediaType.parse("application/json"), "hahhahaha")
    val formBuilder = FormBody.Builder()
    formBuilder.add("name1", "value1")
    formBuilder.add("name2", "value2")
    val formBody = formBuilder.build()
    val multipartBuilder = MultipartBody.Builder()
    multipartBuilder.addPart(requestBody)
    multipartBuilder.addPart(formBody)
    val multipartBody = multipartBuilder.build()
    val builder = Request.Builder()
    builder.url("https://www.baidu.com")
        .put(multipartBody)
        .addHeader("hello", "world")
        .header("hello1", "yesheng")
        .cacheControl(CacheControl.FORCE_NETWORK)
        .tag("first")
    val request = builder.build()
    val call = OkHttpUtils.defaultOkHttpClient.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println(e.message)
        }

        @Throws(IOException::class)
        override fun onResponse(
            call: Call,
            response: Response
        ) {
            println(response.body()!!.string())
        }
    })
}
