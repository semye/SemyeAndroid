package com.semye.android.module.main.datasource

import com.semye.android.module.item10_rxjava.RxjavaMainActivity
import com.semye.android.module.item11_arouter.ARouterMainActivity
import com.semye.android.module.item12_jni.JniActivity
import com.semye.android.module.item13_weight.WeightActivity
import com.semye.android.module.item14_animation.AnimationMainActivity
import com.semye.android.module.item15_handler.HandlerActivity
import com.semye.android.module.item16_asynctask.AsyncTaskActivity
import com.semye.android.module.item17_environment.EnvironmentActivity
import com.semye.android.module.item18_countdowntimer.CountDownTimerActivity
import com.semye.android.module.item19_systemui.SystemUiActivity
import com.semye.android.module.item1_lifecycle.LifecycleActivity
import com.semye.android.module.item20_fitsystemwindow.FitSystemWindowActivity
import com.semye.android.module.item21_recyclerview.RecyclerViewMainActivity
import com.semye.android.module.item22_actionbar.ActionBarMainActivity
import com.semye.android.module.item23_fresco.FrescoMainActivity
import com.semye.android.module.item24_glide.GlideMainActivity
import com.semye.android.module.item25_wifi.WifiMainActivity
import com.semye.android.module.item26_viewmodel.ViewModelMainActivity
import com.semye.android.module.item27_connectivitymanager.ConnectMainActivity
import com.semye.android.module.item28_okhttp.OkhttpMainActivity
import com.semye.android.module.item29_retrofit.Retrofit2MainActivity
import com.semye.android.module.item2_launch.StandardActivity
import com.semye.android.module.item30_window.WindowMainActivity
import com.semye.android.module.item31_statusbar.StatusBarActivity
import com.semye.android.module.item32_like.LikeActivity
import com.semye.android.module.item33_flutter.FlutterMainActivity
import com.semye.android.module.item35_xml.XMLMainActivity
import com.semye.android.module.item3_vieweventdispatch.TouchEventActivity
import com.semye.android.module.item4_service.ServiceMainActivity
import com.semye.android.module.item5_thread.ThreadActivity
import com.semye.android.module.item6_os.OSActivity
import com.semye.android.module.item7_custom_view.CustomViewActivity
import com.semye.android.module.item8_coroutines.CoroutinesMainActivity
import com.semye.android.module.item9_eventbus.EventBusMainActivity

import javax.inject.Inject

class MainLocalDataSource @Inject constructor() {

    fun requestListData(): List<Pair<String, Class<*>>> {
        return mutableMapOf<String, Class<*>>().apply {
            this["生命周期"] = LifecycleActivity::class.java
            this["启动模式"] = StandardActivity::class.java
            this["事件分发"] = TouchEventActivity::class.java
            this["服务"] = ServiceMainActivity::class.java
            this["线程"] = ThreadActivity::class.java
            this["os"] = OSActivity::class.java
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
//            this["dagger2"] = Dagger2SampleActivity::class.java
            this["打开flutter界面"] = FlutterMainActivity::class.java
            this["xml解析方式"] = XMLMainActivity::class.java
        }.toList()
    }
}