package com.semye.android.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by yesheng on 2018/10/25.
 */
public class StringConverterFactory extends Converter.Factory {

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return ToStringConverter.INSTANCE;
        }
        return null;
    }

    static final class ToStringConverter implements Converter<ResponseBody, String> {
        static final ToStringConverter INSTANCE = new ToStringConverter();

        @Override
        public String convert(ResponseBody value) {
            try {
                return value.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
