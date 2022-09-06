package com.semye.android.thirdparty.dragger2.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Inject

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