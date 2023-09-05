package com.semye.android.module.item29_retrofit

import com.semye.android.module.item29_retrofit.calladapter.SemyeCall
import com.semye.android.module.item29_retrofit.converter.SemyeResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.QueryName

interface ApiService {
    // 拼接结果 ?name=name&password=password
    @GET("/")
    fun getService(@Query("name") name: String?, @Query("password") password: String?): Call<ResponseBody>?

    // 拼接结果 /?name&password
    @GET("/")
    fun getService2(@QueryName name: String?, @QueryName password: String?): Call<ResponseBody>?

    // 拼接结果 /?name&password
    @GET("/")
    fun getService3(@QueryMap map: Map<String, String>?): Call<ResponseBody>?

    //表单请求
    @FormUrlEncoded
    @POST("/submit")
    fun getService4(@Field("name") name: String?, @Field("password") password: String?): Call<ResponseBody>?

    //多part请求
    @Multipart
    @POST
    fun getService5(@Part("part1") part1: String?, @Part("part2") requestBody: RequestBody?): Call<ResponseBody>?

    @GET("/")
    fun getService6(@Query("name") name: String?, @Query("password") password: String?): SemyeCall<SemyeResponse<String>>?

}