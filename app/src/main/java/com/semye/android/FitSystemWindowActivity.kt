package com.semye.android

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

/**
 *  Created by yesheng on 2020/9/27
 *  fitsSystemWindows 只有在状态栏透明时才生效
 *  fitsSystemWindows 为true时会使布局的padding失效
 *  默认值为false
 */
class FitSystemWindowActivity : AppCompatActivity(), View.OnClickListener {

    private var mRoot: LinearLayout? = null
    private var mButton1: Button? = null
    private var mButton2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)//设置状态栏透明
        setContentView(R.layout.activity_fit_system)
        mRoot = findViewById(R.id.root)
        mButton1 = findViewById(R.id.button1)
        mButton2 = findViewById(R.id.button2)
        mButton1?.setOnClickListener(this)
        mButton2?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                mRoot?.fitsSystemWindows = true
            }
            R.id.button2 -> {
                mRoot?.fitsSystemWindows = false
            }
        }
    }
}