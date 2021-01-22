package com.semye.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

class TouchEventLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    /**
     * false 交给Activity处理
     * true  拦截不下发
     * super.dispatchTouchEvent(ev) 交给子view处理
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        println("viewgroup dispatch touch event " + ev?.action)
        return super.dispatchTouchEvent(ev)
    }

    /**
     * true 拦截分发屏幕触摸事件
     * false 不拦截屏幕触摸事件
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        println("viewgroup intercept touch event " + ev?.action)
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * true layout处理触摸事件
     * false 交给Activity处理
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("viewgroup consume " + event?.action)
        return super.onTouchEvent(event)
    }
}