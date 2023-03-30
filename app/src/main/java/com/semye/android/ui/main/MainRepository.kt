package com.semye.android.ui.main

import com.semye.android.ui.animation.AnimationMainActivity
import com.semye.android.ui.app.ActionBarMainActivity
import com.semye.android.ui.app.service.ServiceMainActivity
import com.semye.android.module.bluetooth.BluetoothMainActivity
import com.semye.android.ui.coroutines.CoroutinesMainActivity
import com.semye.android.ui.jetpack.recyclerview.RecyclerViewMainActivity
import com.semye.android.ui.jetpack.viewmodel.ViewModelMainActivity
import com.semye.android.ui.jni.JniActivity
import com.semye.android.ui.launch.StandardActivity
import com.semye.android.ui.lifecycle.LifeCycleActivity
import com.semye.android.ui.like.LikeActivity
import com.semye.android.ui.network.ConnectMainActivity
import com.semye.android.ui.wifi.WifiMainActivity
import com.semye.android.ui.os.AsyncTaskActivity
import com.semye.android.ui.os.CountDownTimerActivity
import com.semye.android.ui.os.EnvironmentActivity
import com.semye.android.ui.os.OSActivity
import com.semye.android.ui.os.handler.HandlerActivity
import com.semye.android.ui.thirdparty.dagger2.Dagger2SampleActivity
import com.semye.android.ui.thirdparty.eventbus.EventBusMainActivity
import com.semye.android.ui.thirdparty.fresco.FrescoMainActivity
import com.semye.android.ui.thirdparty.glide.GlideMainActivity
import com.semye.android.ui.thirdparty.okhttp.OkhttpMainActivity
import com.semye.android.ui.thirdparty.retrofit.Retrofit2MainActivity
import com.semye.android.ui.thirdparty.rxjava.RxjavaMainActivity
import com.semye.android.ui.threa.ThreadActivity
import com.semye.android.ui.view.CustomViewActivity
import com.semye.android.ui.view.SystemUiActivity
import com.semye.android.ui.view.WeightActivity
import com.semye.android.ui.view.WindowMainActivity
import com.semye.android.ui.view.fitsystemwindow.FitSystemWindowActivity
import com.semye.android.ui.view.statusbar.StatusBarActivity
import com.semye.android.ui.view.touchevent.TouchEventActivity

/**
 *  Created by yesheng on 2020/11/5
 *  请求网络或者查询数据库获取数据
 */
class MainRepository {
    fun requestListData(): List<Pair<String, Class<*>>> {
        return mutableMapOf<String, Class<*>>().apply {
            this["生命周期"] = LifeCycleActivity::class.java
            this["启动模式"] = StandardActivity::class.java
            this["事件分发"] = TouchEventActivity::class.java
            this["服务"] = ServiceMainActivity::class.java
            this["线程"] = ThreadActivity::class.java
            this["os"] = OSActivity::class.java
            this["蓝牙"] = BluetoothMainActivity::class.java
            this["custom"] = CustomViewActivity::class.java
            this["coroutines"] = CoroutinesMainActivity::class.java
            this["eventbus"] = EventBusMainActivity::class.java
            this["rxjava"] = RxjavaMainActivity::class.java
            this["arouter"] = com.semye.android.ui.thirdparty.arouter.ARouterMainActivity::class.java
            this["jni"] = JniActivity::class.java
            this["weight"] = WeightActivity::class.java
            this["Animation"] = AnimationMainActivity::class.java
            this["Handler机制"] = HandlerActivity::class.java
            this["AsyncTask"] = AsyncTaskActivity::class.java
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
            this["点赞特效"] = LikeActivity::class.java
            this["dagger2"] = Dagger2SampleActivity::class.java
        }.toList()
    }
}