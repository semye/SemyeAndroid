package com.semye.android.ui.item7_custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View

/**
 * Created by yesheng on 2017/3/6.
 */
class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var rectF = RectF(0f, 0f, 100f, 100f)
    private var rectoval = RectF(100f, 100f, 800f, 800f)
    private var paint = Paint()
    private var path = Path().apply {
        moveTo(50f, 100f) //起始点
        lineTo(50f, 300f) //连线到下一点
        lineTo(100f, 500f) //连线到下一点
        lineTo(400f, 500f) //连线到下一点
        lineTo(300f, 300f) //连线到下一点
        lineTo(450f, 50f) //连线到下一点
        lineTo(200f, 200f)
    }

    private fun initPaint() {
        paint.isAntiAlias = true //设置抗锯齿
        paint.color = resources.getColor(android.R.color.white)
    }

    init {
        initPaint()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        println("从xml加载完成")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //画布
        //从下往上一层一层绘制
//        canvas.drawARGB(255, 163, 159, 163) //绘制控件的颜色
//        canvas.drawARGB(155, 0, 255, 0) //绘制控件的颜色

//        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), 20f, paint) //绘制圆形

        //角度
        //      270
        //  180     0
        //      90
//        canvas.drawArc(rectF, 180f, 360f, true, paint)//绘制一个扇形
//        canvas.drawOval(rectoval, paint)//绘制椭圆
//        canvas.drawLine(0f, 0f, 800f, 800f, paint)//绘制直线
        canvas.drawRect(
            280f, 280f,
            400f,
            400f,
            paint
        )//绘制矩形
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            canvas.drawRoundRect(100f, 800f, 800f, 1200f, 50f, 50f, paint)
        }//圆角的矩形
//        canvas.drawPath(path, paint)//用于绘制多边形
        paint.textSize = 40f
//        canvas.drawText("fuck", 20f, 30f, paint)
//        canvas.save()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        println("onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        println("onDetachedFromWindow")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun onTrackballEvent(event: MotionEvent?): Boolean {
        return super.onTrackballEvent(event)
    }

    companion object {
        const val TAG = "CustomView"
    }
}