package com.semye.android.ui.view.statusbar

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.semye.android.R

/**
 * 状态栏相关
 */
class StatusBarActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mButton1: AppCompatButton
    private lateinit var mButton2: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar)
        mButton1 = findViewById(R.id.btn1)
        mButton2 = findViewById(R.id.btn2)
        mButton1.setOnClickListener(this)
        mButton2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                StatusBarCompat.setTranslucentStatusBar(window)
                //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (color == Color.TRANSPARENT) {
//                    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                } else {
//                    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                }
//            }
            }
            R.id.btn2 -> {
                StatusBarCompat.setStatusBarColor(window, Color.BLACK)
            }
        }
    }
}

const val TAG = "StatusBarActivity"