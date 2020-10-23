package com.semye.android.retrofit;


import androidx.annotation.NonNull;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yesheng on 2018/9/15.
 */
public class MyCall implements Call.Factory {

    @NonNull
    @Override
    public Call newCall(@NonNull Request request) {
        return new OkHttpClient().newCall(request);
    }
}
