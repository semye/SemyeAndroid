package com.semye.android.view.touchevent

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

class TouchEventLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    /**
     * false 交给父容器的onTouch方法处理
     * true  当前方法消费掉
     * super 交给onInterceptTouchEvent方法判断是否拦截
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("yesheng","viewgroup dispatch touch event " + ev?.action)
        return super.dispatchTouchEvent(ev)
    }

    /**
     * true 拦截分发屏幕触摸事件，当前ViewGroup的onTouch方法
     * false/super 不拦截屏幕触摸事件,如果有子view,交给处理，没有则回调当前ViewGroup的onTouch方法
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("yesheng","viewgroup intercept touch event " + ev?.action)
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * true layout处理触摸事件
     * false 交给Activity处理
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("yesheng","viewgroup consume " + event?.action)
        return super.onTouchEvent(event)
    }
}