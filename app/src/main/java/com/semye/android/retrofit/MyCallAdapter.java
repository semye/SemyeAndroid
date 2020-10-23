package com.semye.android.retrofit;


import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Created by yesheng on 2018/9/18.
 */
public class MyCallAdapter extends CallAdapter.Factory {

    @Nullable
    @Override
    public CallAdapter<?, ?> get(@Nullable final Type returnType, @Nullable Annotation[] annotations, @Nullable Retrofit retrofit) {
        if (returnType == String.class) {
            return new CallAdapter<Object, Object>() {
                @Override
                public Type responseType() {
                    return getParameterUpperBound(0, (ParameterizedType) returnType);
                }

                @Nullable
                @Override
                public Object adapt(@Nullable Call<Object> call) {
                    return null;
                }
            };
        }
        return null;
    }
}
