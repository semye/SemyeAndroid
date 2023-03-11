package com.semye.android.thirdparty.dagger2.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides

/**
 * Created by yesheng on 2017/3/9.
 */
@Module
class MainModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

}