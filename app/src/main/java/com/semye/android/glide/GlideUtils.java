package com.semye.android.glide;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;


/**
 * Created by yesheng on 2018/9/3.
 */
public class GlideUtils {


    public static void displayImage(@NonNull Context context, String url, @NonNull ImageView imageView) {
        //GlideApp 使用注解生成的API 替换Glide
//        GlideApp.with(context).load(url).into(imageView);
    }


}
