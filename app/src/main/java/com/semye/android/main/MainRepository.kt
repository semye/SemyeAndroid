package com.semye.android.main

import com.semye.android.animation.AnimationMainActivity
import com.semye.android.app.ActionBarMainActivity
import com.semye.android.app.service.ServiceMainActivity
import com.semye.android.coroutines.CoroutinesMainActivity
import com.semye.android.jetpack.recyclerview.RecyclerViewMainActivity
import com.semye.android.jetpack.viewmodel.ViewModelMainActivity
import com.semye.android.jni.JniActivity
import com.semye.android.network.ConnectMainActivity
import com.semye.android.network.wifi.WifiMainActivity
import com.semye.android.os.AsyncTaskActivity
import com.semye.android.os.CountDownTimerActivity
import com.semye.android.os.EnvironmentActivity
import com.semye.android.os.handler.HandlerActivity
import com.semye.android.thirdparty.arouter.ARouterMainActivity
import com.semye.android.thirdparty.eventbus.EventBusMainActivity
import com.semye.android.thirdparty.fresco.FrescoMainActivity
import com.semye.android.thirdparty.glide.GlideMainActivity
import com.semye.android.thirdparty.okhttp.OkhttpMainActivity
import com.semye.android.thirdparty.retrofit.Retrofit2MainActivity
import com.semye.android.thirdparty.rxjava.RxjavaMainActivity
import com.semye.android.view.CustomViewActivity
import com.semye.android.view.SystemUiActivity
import com.semye.android.view.WeightActivity
import com.semye.android.view.WindowMainActivity
import com.semye.android.view.fitsystemwindow.FitSystemWindowActivity
import com.semye.android.view.statusbar.StatusBarActivity

/**
 *  Created by yesheng on 2020/11/5
 *  请求网络或者查询数据库获取数据
 */
class MainRepository {
    fun requestListData(): List<Pair<String, Class<*>>> {
        return mutableMapOf<String, Class<*>>().apply {
            this["custom"] = CustomViewActivity::class.java
            this["coroutines"] = CoroutinesMainActivity::class.java
            this["eventbus"] = EventBusMainActivity::class.java
            this["rxjava"] = RxjavaMainActivity::class.java
            this["arouter"] = ARouterMainActivity::class.java
            this["jni"] = JniActivity::class.java
            this["weight"] = WeightActivity::class.java
            this["Animation"] = AnimationMainActivity::class.java
            this["Handler机制"] = HandlerActivity::class.java
            this["AsyncTask"] = AsyncTaskActivity::class.java
            this["Service"] = ServiceMainActivity::class.java
            this["environment"] = EnvironmentActivity::class.java
            this["count_down_timer"] = CountDownTimerActivity::class.java
            this["systemui"] = SystemUiActivity::class.java
            this["fitsystemwindow"] = FitSystemWindowActivity::class.java
            this["recyclerview"] = RecyclerViewMainActivity::class.java
            this["actionbar"] = ActionBarMainActivity::class.java
            this["fresco"] = FrescoMainActivity::class.java
            this["glide"] = GlideMainActivity::class.java
            this["wifi"] = WifiMainActivity::class.java
            this["viewmodel"] = ViewModelMainActivity::class.java
            this["connectivityManager"] = ConnectMainActivity::class.java
            this["okhttp"] = OkhttpMainActivity::class.java
            this["retrofit"] = Retrofit2MainActivity::class.java
            this["window"] = WindowMainActivity::class.java
            this["statusbar"] = StatusBarActivity::class.java
        }.toList()
    }
}