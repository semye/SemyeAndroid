package com.semye.android.ui.item7_custom_view

import android.graphics.Color
import android.graphics.Outline
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class ClipToOutlineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)
        val view1 = findViewById<View>(R.id.view1)
        val view2 = findViewById<View>(R.id.view2)
        val view3 = findViewById<View>(R.id.view3)
        val view4 = findViewById<View>(R.id.view4)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view1.outlineProvider = CustomViewOutlineProvider()
            view1.clipToOutline = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            view2.outlineProvider = BackgroundViewOutlineProvider()
//            view2.clipToOutline = true
            val gradient = GradientDrawable()
            gradient.shape = GradientDrawable.RECTANGLE
            gradient.gradientType = GradientDrawable.RADIAL_GRADIENT
            gradient.setColor(Color.BLACK)
            gradient.gradientRadius = 24f
            gradient.cornerRadius = 24f
            gradient.cornerRadii = floatArrayOf(60f, 60f, 60f, 60f, 60f, 60f, 60f, 60f)
            gradient.setStroke(12, Color.GREEN)
            view2.background = gradient
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view3.outlineProvider = OvalViewOutlineProvider()
            view2.clipToOutline = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view4.clipToOutline = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private class CustomViewOutlineProvider : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(Rect(0, 0, view.width, view.height), 24f)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private class OvalViewOutlineProvider : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            var mlt = 100;
            var cw = view.height;
            var ch = cw;

            var mleft = (view.width - cw) / 2;
            var mtop = mlt;

            cw = ch - mtop;
            ch = cw;

            var mright = mleft + cw;
            var mbottom = ch + mtop;
            outline.setOval(mleft, mtop, mright, mbottom)
            view.invalidate()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private class BackgroundViewOutlineProvider : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
//            val drawable = view.background
            outline.setRoundRect(Rect(0, 0, view.width, view.height), 24f)
//            drawable?.getOutline(outline)
        }
    }
}