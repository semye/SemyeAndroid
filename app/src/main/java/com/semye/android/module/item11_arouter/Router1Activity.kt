package com.semye.android.module.item11_arouter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.semye.android.R

/**
 *  Created by yesheng on 2020/11/27
 */
@Route(path = "/semye/route1")
class Router1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router_1)
    }
}