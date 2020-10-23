package com.semye.android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import com.semye.android.animation.AnimationMainActivity
import com.semye.android.recyclerview.RecyclerViewMainActivity

/**
 *  Created by yesheng on 2020/9/23
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mDayNightButton: Button? = null
    private var mHandlerButton: Button? = null
    private var mAsyncTaskButton: Button? = null
    private var mEnvironmentButton: Button? = null
    private var mCountDownTimerButton: Button? = null
    private var mLooperButton: Button? = null
    private var mSystemUIButton: Button? = null
    private var mFitSystemWindowButton: Button? = null
    private var mRecyclerViewButton: Button? = null
    private var mActionBarButton: Button? = null
    private var mWifiButton: Button? = null
    private var mAnimationButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDayNightButton = findViewById(R.id.daynight)
        mHandlerButton = findViewById(R.id.handler)
        mAsyncTaskButton = findViewById(R.id.async_task)
        mEnvironmentButton = findViewById(R.id.environment)
        mCountDownTimerButton = findViewById(R.id.count_down_timer)
        mLooperButton = findViewById(R.id.looper)
        mSystemUIButton = findViewById(R.id.systemui)
        mFitSystemWindowButton = findViewById(R.id.fitsystemwindow)
        mRecyclerViewButton = findViewById(R.id.recyclerview)
        mActionBarButton = findViewById(R.id.actionbar)
        mAnimationButton = findViewById(R.id.animation)
        mWifiButton = findViewById(R.id.wifi)
        mHandlerButton?.setOnClickListener(this)
        mAsyncTaskButton?.setOnClickListener(this)
        mEnvironmentButton?.setOnClickListener(this)
        mCountDownTimerButton?.setOnClickListener(this)
        mLooperButton?.setOnClickListener(this)
        mSystemUIButton?.setOnClickListener(this)
        mFitSystemWindowButton?.setOnClickListener(this)
        mDayNightButton?.setOnClickListener(this)
        mRecyclerViewButton?.setOnClickListener(this)
        mActionBarButton?.setOnClickListener(this)
        mWifiButton?.setOnClickListener(this)
        mAnimationButton?.setOnClickListener(this)
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        println(
            metrics.widthPixels
        )
        println(
            "1dp 等于" + TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1f,
                metrics
            ) + "像素"
        )
        println(
            "1sp 等于" + TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 1f,
                metrics
            ) + "像素"
        )

        println(
            "设备开机到现在的毫秒数,包括休眠时间===>" + SystemClock.elapsedRealtime()
        )
        println(
            "设备开机到现在的毫秒数,不包括休眠时间====>" + SystemClock.uptimeMillis()
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.daynight -> {
                if (delegate.localNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                } else {
                    delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
                }
            }
            R.id.handler -> {
                val intent = Intent()
                intent.setClass(this, HandlerActivity::class.java)
                startActivity(intent)
            }
            R.id.async_task -> {
                val intent = Intent()
                intent.setClass(this, AsyncTaskActivity::class.java)
                startActivity(intent)
            }
            R.id.environment -> {
                val intent = Intent()
                intent.setClass(this, EnvironmentActivity::class.java)
                startActivity(intent)
            }
            R.id.count_down_timer -> {
                val intent = Intent()
                intent.setClass(this, CountDownTimerActivity::class.java)
                startActivity(intent)
            }
            R.id.looper -> {
                val intent = Intent()
                intent.setClass(this, LooperActivity::class.java)
                startActivity(intent)
            }
            R.id.systemui -> {
                val intent = Intent()
                intent.setClass(this, SystemUiActivity::class.java)
                startActivity(intent)
            }
            R.id.fitsystemwindow -> {
                val intent = Intent()
                intent.setClass(this, FitSystemWindowActivity::class.java)
                startActivity(intent)
            }
            R.id.recyclerview -> {
                val intent = Intent()
                intent.setClass(this, RecyclerViewMainActivity::class.java)
                startActivity(intent)
            }
            R.id.actionbar -> {
                val intent = Intent()
                intent.setClass(this, ActionBarMainActivity::class.java)
                startActivity(intent)
            }
            R.id.wifi -> {
                val intent = Intent()
                intent.setClass(this, WifiMainActivity::class.java)
                startActivity(intent)
            }
            R.id.animation -> {
                val intent = Intent()
                intent.setClass(this, AnimationMainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}