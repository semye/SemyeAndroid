package com.semye.android.like

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationSet
import androidx.core.animation.addListener
import com.semye.android.R

/**
 * 点赞view
 */
class LikeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val likeViews = intArrayOf(R.drawable.earphone,
            R.drawable.flowers,
            R.drawable.haha_1,
            R.drawable.haha_2,
            R.drawable.haha_3,
            R.drawable.haha_4,
            R.drawable.hello,
            R.drawable.like,
            R.drawable.love,
            R.drawable.love_wings,
            R.drawable.star)

    private var mEarphoneBitmap: Bitmap? = null
    private var mflowersBitmap: Bitmap? = null
    private var mHaha1Bitmap: Bitmap? = null
    private var mHaha2Bitmap: Bitmap? = null
    private var mHaha3Bitmap: Bitmap? = null
    private var mHaha4Bitmap: Bitmap? = null
    private var mHelloBitmap: Bitmap? = null
    private var mLikeBitmap: Bitmap? = null
    private var mLoveBitmap: Bitmap? = null
    private var mLoveWingsBitmap: Bitmap? = null
    private var mStarBitmap: Bitmap? = null

    private var mMatrix = Matrix()

    private var listLikeIcon = mutableListOf<LikeIcon>()
    private var listBitmap = mutableListOf<Bitmap>()


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mEarphoneBitmap = BitmapFactory.decodeResource(resources, R.drawable.earphone)
        listBitmap.add(mEarphoneBitmap!!)
        mflowersBitmap = BitmapFactory.decodeResource(resources, R.drawable.flowers)
        listBitmap.add(mflowersBitmap!!)
        mHaha1Bitmap = BitmapFactory.decodeResource(resources, R.drawable.haha_1)
        listBitmap.add(mHaha1Bitmap!!)
        mHaha2Bitmap = BitmapFactory.decodeResource(resources, R.drawable.haha_2)
        listBitmap.add(mHaha2Bitmap!!)
        mHaha3Bitmap = BitmapFactory.decodeResource(resources, R.drawable.haha_3)
        listBitmap.add(mHaha3Bitmap!!)
        mHaha4Bitmap = BitmapFactory.decodeResource(resources, R.drawable.haha_4)
        listBitmap.add(mHaha4Bitmap!!)
        mHelloBitmap = BitmapFactory.decodeResource(resources, R.drawable.hello)
        listBitmap.add(mHelloBitmap!!)
        mLikeBitmap = BitmapFactory.decodeResource(resources, R.drawable.like)
        listBitmap.add(mLikeBitmap!!)
        mLoveBitmap = BitmapFactory.decodeResource(resources, R.drawable.love)
        listBitmap.add(mLoveBitmap!!)
        mLoveWingsBitmap = BitmapFactory.decodeResource(resources, R.drawable.love_wings)
        listBitmap.add(mLoveWingsBitmap!!)
        mStarBitmap = BitmapFactory.decodeResource(resources, R.drawable.star)
        listBitmap.add(mStarBitmap!!)
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mEarphoneBitmap?.recycle()
        mflowersBitmap?.recycle()
        mHaha1Bitmap?.recycle()
        mHaha2Bitmap?.recycle()
        mHaha3Bitmap?.recycle()
        mHaha4Bitmap?.recycle()
        mHelloBitmap?.recycle()
        mLikeBitmap?.recycle()
        mLoveBitmap?.recycle()
        mLoveWingsBitmap?.recycle()
        mStarBitmap?.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("yesheng", "canvas=====>")
        listLikeIcon.forEach { likeIcon ->
            canvas?.let {
                it.drawBitmap(likeIcon.bitmap, likeIcon.matrix, null)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            Log.e("yesheng", "x=====>" + event?.rawX + ",y====>" + event?.y)
            val matrix = Matrix()
            matrix.setTranslate(event.x, event.y)
            val likeIcon = LikeIcon(event!!, listBitmap.random(), matrix)
            listLikeIcon.add(likeIcon)
            test(likeIcon)

        }
        return super.onTouchEvent(event)
    }

    private fun test(likeIcon: LikeIcon) {
        val animiSet = AnimatorSet()
        val scale = ValueAnimator.ofFloat(0f, 1.4f, 1f)//缩放变化
        scale.addUpdateListener {
            likeIcon.matrix.setScale(it.animatedValue as Float, it.animatedValue as Float)
            likeIcon.matrix.postTranslate(likeIcon.event.x, likeIcon.event.y)
            invalidate()
        }
        val rotate = ValueAnimator.ofFloat(0f, 275f, 45f)//旋转变化
        rotate.addUpdateListener {
//            likeIcon.matrix.setRotate(it.animatedValue as Float)
            invalidate()
        }
        animiSet.playTogether(scale, rotate)
        animiSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                listLikeIcon.remove(likeIcon)
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }

        })
        animiSet.duration = 500
        animiSet.start()
    }

    data class LikeIcon(
            var event: MotionEvent,
            var bitmap: Bitmap,
            var matrix: Matrix
    )

}