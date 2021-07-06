/*
 * Copyright (c) 2018. Semye
 */
package com.semye.android.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * 重写ViewPager解决在ScrollView中滑动冲突问题
 *
 *
 * Created by yesheng on 2017/1/6.
 */
class MyViewPager : ViewPager {
    /**
     * 触摸时按下的点
     */
    var downP = PointF()

    /**
     * 触摸时当前的点
     */
    var curP = PointF()
    var onSingleTouchListener: OnSingleTouchListener? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context) : super(context) {}

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //每次进行onTouch事件都记录当前的按下的坐标
        curP.x = event.x
        curP.y = event.y
        if (event.action == MotionEvent.ACTION_DOWN) {
            //记录按下时候的坐标  
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变  
            downP.x = event.x
            downP.y = event.y
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰  
            parent.requestDisallowInterceptTouchEvent(true)
        }
        if (event.action == MotionEvent.ACTION_MOVE) {
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰  
            parent.requestDisallowInterceptTouchEvent(true)
        }
        if (event.action == MotionEvent.ACTION_UP) {
            //在up时判断是否按下和松手的坐标为一个点  
            //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick  
            if (downP.x == curP.x && downP.y == curP.y) {
                onSingleTouch()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 单击
     */
    fun onSingleTouch() {
        if (onSingleTouchListener != null) {
            onSingleTouchListener!!.onSingleTouch()
        }
    }

    /**
     * 创建点击事件接口
     *
     * @author wanpg
     */
    interface OnSingleTouchListener {
        fun onSingleTouch()
    }


}