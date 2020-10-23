package com.semye.android.retrofit;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;

public interface ApiServices {

    // 拼接结果 ?name=name&password=password
    @GET("/")
    Call<ResponseBody> getService(@Query("name") String name, @Query("password") String password);

    // 拼接结果 /?name&password
    @GET("/")
    Call<ResponseBody> getService2(@QueryName String name, @QueryName String password);

    // 拼接结果 /?name&password
    @GET("/")
    Call<ResponseBody> getService3(@QueryMap Map<String, String> map);


    //表单请求
    @FormUrlEncoded
    @POST("/submit")
    Call<ResponseBody> getService4(@Field("name") String name, @Field("password") String password);


    //多part请求
    @Multipart
    @POST
    Call<ResponseBody> getService5(@Part("part1") String part1, @Part("part2") RequestBody requestBody);

}
