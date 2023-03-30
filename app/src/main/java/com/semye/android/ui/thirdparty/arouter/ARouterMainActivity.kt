package com.semye.android.ui.thirdparty.arouter

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.semye.android.R

/**
 *  Created by yesheng on 2020/11/27
 *  path 路径至少两个层级 第一个层级默认为group
 */
class ARouterMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBtn: Button
    private lateinit var mBtn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arouter)
        mBtn = findViewById(R.id.btn1)
        mBtn2 = findViewById(R.id.btn2)
        mBtn.setOnClickListener(this)
        mBtn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                ARouter.getInstance().build("/semye/route1").navigation()
            }
            R.id.btn2 -> {
                //如果有多个service实现类,默认使用最后一个注册的实现类
                val rout = ARouter.getInstance().navigation(com.semye.android.ui.thirdparty.arouter.RouteService::class.java)
                println(rout)
            }
        }
    }
}