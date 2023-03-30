package com.semye.android.ui.thirdparty.dagger2.module

import com.google.gson.Gson
import com.semye.android.ui.thirdparty.dagger2.model.Model
import com.semye.android.ui.thirdparty.dagger2.model.MyModel
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by yesheng on 2017/3/9.
 */
@Module
abstract class MainModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideGson(): Gson {
            return Gson()
        }
    }


    @Binds
    abstract fun bindmodel(model: MyModel): Model

}