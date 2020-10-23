/*
 * Copyright (c) 2018. Semye
 */

package com.semye.android.retrofit;


import androidx.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yesheng on 2018/9/3.
 */
public final class RetrofitHelper {

    private RetrofitHelper() {
    }

    public static <T> T create(Class<T> service, @NonNull CookieJar cookieJar) {
        Annotation[] annotations = service.getAnnotations();
        String baseUrl = "";
        for (Annotation annotation : annotations) {
            if (annotation instanceof BaseUrl) {
                baseUrl = annotation.toString();
            }
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }
}
