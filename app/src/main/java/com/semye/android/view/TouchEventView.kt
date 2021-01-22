package com.semye.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchEventView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     * true 自己处理触摸事件 通常交给onTouchEvent处理
     * false 交给ViewGroup处理
     */
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        println("view dispatch touch event " + event?.action)
        return super.dispatchTouchEvent(event)
    }

    /**
     *  true 处理触摸事件
     *  false 不处理触摸事件,交给ViewGroup处理
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        println("view consume " + event?.action)
        return super.onTouchEvent(event)
    }
}