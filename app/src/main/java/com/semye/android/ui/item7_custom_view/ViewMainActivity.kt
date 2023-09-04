package com.semye.android.ui.item7_custom_view

import android.graphics.Color
import android.graphics.Outline
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewOutlineProvider
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R


class ViewMainActivity : AppCompatActivity(), View.OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val view = findViewById<CustomView>(R.id.view)
        view.setOnTouchListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            view.clipBounds = Rect(10, 10, 300, 300)
        }
        view.setBackgroundColor(Color.parseColor("#00ff00"))
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)

//        view.rotation = 90f
        btn1.setOnClickListener {
            view.scrollBy(50, 0)
        }
        btn2.setOnClickListener {
            view.scrollBy(-50, 0)
        }
        btn3.setOnClickListener {
            view.scrollBy(0, 50)
        }
        btn4.setOnClickListener {
            view.scrollBy(0, -50)
        }
        btn5.setOnClickListener {
            println("window===>" + window.toString())
            println("left====>" + view.left)
            println("top====>" + view.top)
            println("right====>" + view.right)
            println("bottom====>" + view.bottom)
            println("x====>" + view.x)
            println("y====>" + view.y)
            println("width====>" + view.width)
            println("height====>" + view.height)
            println("translationX====>" + view.translationX)
            println("translationY====>" + view.translationY)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                println("clipbounds====>" + view.clipBounds.toString())
            }
        }
        btn6.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.clipToOutline = !view.clipToOutline
            }
        }
        btn7.setOnClickListener {
            view.translationX = view.translationX + 10
        }
        btn8.setOnClickListener {
            view.translationX = view.translationX - 10
        }

//        view.clipBounds = Rect(20, 20, 100, 100)
//        view.translationX = 100f
//        view.translationY = 100f
        view.setOnClickListener {
            Toast.makeText(this@ViewMainActivity, "hello", Toast.LENGTH_SHORT).show()
        }

        view?.invalidate()
        view?.requestLayout()
        view?.getLocalVisibleRect(Rect())
        view?.getLocationOnScreen(IntArray(2))
        view?.post {  }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.outlineProvider = CustomViewOutlineProvider()
            view.clipToOutline = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val clipBounds = view.clipBounds
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private class CustomViewOutlineProvider : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
//            outline.setRoundRect(Rect(0, 0, view.width, view.height), 24f)
            outline.setOval(Rect(0, 0, view.width, view.height))
        }
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        println("====>" + event?.x)
        println("====>" + event?.y)
        println("====>" + event?.rawX)//相对屏幕
        println("====>" + event?.rawY)
        return super.onTouchEvent(event)
    }
}
