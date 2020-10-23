package com.semye.android.dragger2;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yesheng on 2017/3/9.
 */
@Module
public class MainModule {


    /**
     * @return 返回注入对象
     * Provides 注解表示这个方法是用来创建某个实例对象的，这里我们创建返回Gson对象
     * 方法名随便，一般用provideXXX结构
     */
    @Provides
    public Gson provideGson() {
        return new Gson();
    }


}
