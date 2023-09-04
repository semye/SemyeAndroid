package com.semye.android.ui.item19_systemui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 *  Created by yesheng on 2020/9/23
 *
 *  todo  这个flag的具体作用 SYSTEM_UI_FLAG_LAYOUT_STABLE
 */
class SystemUiActivity : AppCompatActivity(), View.OnClickListener {
    private var mDecorView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_status)
        mDecorView = window?.decorView
        val tab1 = findViewById<TextView>(R.id.btn1)
        val tab2 = findViewById<TextView>(R.id.btn2)
        val tab3 = findViewById<TextView>(R.id.btn3)
        val tab4 = findViewById<TextView>(R.id.btn4)
        val tab5 = findViewById<TextView>(R.id.btn5)
        val tab6 = findViewById<TextView>(R.id.btn6)
        val tab7 = findViewById<TextView>(R.id.btn7)
        val tab8 = findViewById<TextView>(R.id.btn8)
        val tab81 = findViewById<TextView>(R.id.btn81)
        val tab9 = findViewById<TextView>(R.id.btn9)
        val tab10 = findViewById<TextView>(R.id.btn10)
        val tab11 = findViewById<TextView>(R.id.btn11)
        val tab14 = findViewById<TextView>(R.id.btn14)
        val tab15 = findViewById<TextView>(R.id.btn15)
        tab1.setOnClickListener(this)
        tab2.setOnClickListener(this)
        tab3.setOnClickListener(this)
        tab4.setOnClickListener(this)
        tab5.setOnClickListener(this)
        tab6.setOnClickListener(this)
        tab7.setOnClickListener(this)
        tab8.setOnClickListener(this)
        tab81.setOnClickListener(this)
        tab9.setOnClickListener(this)
        tab10.setOnClickListener(this)
        tab11.setOnClickListener(this)
        tab14.setOnClickListener(this)
        tab15.setOnClickListener(this)
        mDecorView?.setOnSystemUiVisibilityChangeListener { visibility ->
            Log.d(
                TAG,
                "onSystemUiVisibilityChange: $visibility"
            )
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 ->
                mDecorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE  //0
            R.id.btn2 ->
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LOW_PROFILE  //1  设置系统状态栏为低配置  会隐藏运营商等信息
            R.id.btn3 ->
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //2  隐藏导航栏  任何用户交互都会使导航栏可见 并清除全屏flag
            R.id.btn4 ->
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_FULLSCREEN //4 隐藏状态栏 顶部下拉可呼出状态栏 状态栏不会消失
            R.id.btn5 ->
                mDecorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE // 256
            R.id.btn6 ->
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //512  导航栏和状态栏会遮挡布局
            R.id.btn7 ->
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //1024 状态栏会遮挡布局
            R.id.btn8 ->  // 沉浸式模式下隐藏底部导航栏
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //2048
            R.id.btn81 -> // 沉浸式严格模式下隐藏底部导航栏
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //4096
            R.id.btn9 ->  // 沉浸式严格模式下隐藏顶部状态栏
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN//4096
            R.id.btn10 ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mDecorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //8192
                }
            R.id.btn11 ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mDecorView?.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR //16
                }
            R.id.btn14 ->
                // 设置全屏 隐藏导航栏和状态栏   配合 SYSTEM_UI_FLAG_IMMERSIVE 使用
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE
            R.id.btn15 ->
                // 设置全屏 隐藏导航栏和状态栏   配合 SYSTEM_UI_FLAG_IMMERSIVE_STICKY 过几秒后会自动消失
                mDecorView?.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    companion object {
        val TAG = SystemUiActivity::class.java.simpleName
    }
}