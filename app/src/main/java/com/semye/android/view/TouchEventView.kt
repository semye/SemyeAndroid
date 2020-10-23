package com.semye.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi

class TouchEventView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


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