package com.semye.android.retrofit;



import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by yesheng on 2018/8/23.
 * <p>
 * Http工具类
 */
public class HttpUtils {


    @NonNull
    public static Retrofit initRetrofit(@NonNull String url) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(new MyCallAdapter())
                .client(builder.build())
                .build();

        return retrofit;
    }

    public static OkHttpClient getDefaultOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
        return builder.build();
    }


}
