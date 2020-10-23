package com.semye.android.view

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi

/**
 * 自定义ViewGroup,只能包含一个子view
 */
class CustomLayout : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (childCount > 1) return
        val view = getChildAt(0)
        measureChild(
            view, View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec),
            View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (childCount > 1) return
        val view = getChildAt(0)
        view.layout(l, t, r, b)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //viewgroup中 布局有背景颜色才会调用onDraw方法
        println("===>" + canvas?.toString())
    }
}