package com.semye.android

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.webkit.CookieManager
import androidx.multidex.MultiDexApplication
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.semye.android.module.database.DatabaseOperator
import com.semye.android.module.network.AppNetworkManager
import dagger.hilt.android.HiltAndroidApp
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor


/**
 * Created by yesheng on 2020/5/21
 */
@HiltAndroidApp
class SemyeApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

    private var connectivityManager: ConnectivityManager? = null

    lateinit var flutterEngine: FlutterEngine


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.e("yesheng1", "attachBaseContext:" + Log.getStackTraceString(Throwable()))
    }

    override fun onCreate() {
        super.onCreate()
        createFlutterEngine2()
        application = this
        DatabaseOperator.init(this)
        Log.e("yesheng1", Log.getStackTraceString(Throwable()))
        Log.d(TAG, "application create")
        AppNetworkManager.init(this)
//        registerActivityLifecycleCallbacks(this)
    }

    private fun createFlutterEngine2() {
        FlutterBoost.instance().setup(this, object : FlutterBoostDelegate {
            override fun pushNativeRoute(options: FlutterBoostRouteOptions) {

            }

            override fun pushFlutterRoute(options: FlutterBoostRouteOptions) {
                val intent = FlutterBoostActivity.CachedEngineIntentBuilder(
                    FlutterBoostActivity::class.java
                )
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                    .destroyEngineWithActivity(false)
                    .uniqueId(options.uniqueId())
                    .url(options.pageName())
                    .urlParams(options.arguments())
                    .build(FlutterBoost.instance().currentActivity())
                FlutterBoost.instance().currentActivity().startActivity(intent)
            }
        }) { engine: FlutterEngine? -> }
    }

    private fun createFlutterEngine() {
        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        flutterEngine.addEngineLifecycleListener(object : FlutterEngine.EngineLifecycleListener {
            override fun onPreEngineRestart() {
                Log.d(TAG, "onPreEngineRestart")
            }

            override fun onEngineWillDestroy() {
                Log.d(TAG, "onEngineWillDestroy")
            }

        })
        //单例保存flutter引擎
        FlutterEngineCache.getInstance().put("semye", flutterEngine)
//        flutterEngine.destroy()
    }

    private val cookies: HashMap<String, String>
        get() {
            val cookie = CookieManager.getInstance().getCookie("wdzj.com")
            val hashMap = HashMap<String, String>()
            if (!TextUtils.isEmpty(cookie)) {
                val cookieArray = TextUtils.split(cookie, "; ")
                for (a in cookieArray) {
                    val keyValue = TextUtils.split(a, "=")
                    if (keyValue.size == 2) {
                        hashMap[keyValue[0]] = keyValue[1]
                    }
                }
            }
            return hashMap
        }

    private fun cookie() {
        Log.d(TAG, "before cookie")
        //获取CookieManager的单例
        val cookieManager = CookieManager.getInstance()
        //设置这个application的webview是否发送和接收cookie
        cookieManager.setAcceptCookie(true)
        Log.d(TAG, "after cookie")

        /*先去判断cookies数据库是否存在 不存在就去创建该数据库*/
        val value = "semye=19910806;Domain=.wdzj.com;Path=/;Max-Age=94608000"
        cookieManager.setCookie("wdzj.com", value)
        val cookie = CookieManager.getInstance().getCookie("www.wdzj.com")
        Log.d(TAG, "cookie:$cookie")
    }

    companion object {
        const val TAG = "SemyeApplication"
        lateinit var application: Application
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.e("yesheng", "onActivityCreated:$activity")
    }

    override fun onActivityStarted(activity: Activity) {
        Log.e("yesheng", "onActivityStarted:$activity")
    }

    override fun onActivityResumed(activity: Activity) {
        Log.e("yesheng", "onActivityResumed:$activity")
    }

    override fun onActivityPaused(activity: Activity) {
        Log.e("yesheng", "onActivityPaused:$activity")
    }

    override fun onActivityStopped(activity: Activity) {
        Log.e("yesheng", "onActivityStopped:$activity")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.e("yesheng", "onActivitySaveInstanceState:$activity")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.e("yesheng", "onActivityDestroyed:$activity")
    }
}