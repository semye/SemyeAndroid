package com.semye.android.ui.view

import android.content.Context
import android.graphics.Bitmap
import kotlin.jvm.JvmOverloads
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.semye.android.R
import androidx.annotation.RequiresApi

/**
 * Time:12/11/20 4:09 PM
 *
 * @author zhengchengyu
 *
 *
 * Description:
 */
class SatelliteMap : View {
    // 卫星图
    private val satelliteBitmap: Bitmap? = null

    /**
     * 卫星图背景
     */
    private var compassBitmap: Bitmap? = null
    private var resizeBitmap: Bitmap? = null

    /**
     * 背景画笔对象的引用
     */
    private var bgPaint: Paint? = null
    private val cx = 0
    private val cy = 0
    private var compassRadius = 920 / 2
    private var mMatrix: Matrix? = null

    /**
     * 在java代码里new的时候会用到
     *
     * @param context
     */
    constructor(context: Context?) : super(context) {}
    /**
     * 不会自动调用，如果有默认style时，在第二个构造函数中调用
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    /**
     * 在xml布局文件中使用时自动调用
     *
     * @param context
     */
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        //在View的构造方法中通过TypedArray获取
        val res = context.resources
        compassBitmap = BitmapFactory.decodeResource(
            res,
            R.drawable.icon_satellite_bg
        )
        compassRadius = compassBitmap!!.getWidth() / 2
        mMatrix = Matrix()
        bgPaint = Paint()
    }

    /**
     * 只有在API版本>21时才会用到
     * 不会自动调用，如果有默认style时，在第二个构造函数中调用
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (resizeBitmap == null) {
            if (compassBitmap == null) return
            val wscale = width.toFloat() / compassBitmap!!.width.toFloat()
            val hscale = height.toFloat() / compassBitmap!!.height.toFloat()
            mMatrix?.setScale(wscale, hscale)
            resizeBitmap = Bitmap.createBitmap(
                compassBitmap!!,
                0,
                0,
                compassBitmap!!.width,
                compassBitmap!!.height,
                mMatrix,
                true
            )
        }
        if (resizeBitmap!!.isRecycled) return
        canvas.drawBitmap(resizeBitmap!!, 0f, 0f, bgPaint)
    }
}