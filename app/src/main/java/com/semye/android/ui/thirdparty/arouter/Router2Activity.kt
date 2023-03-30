package com.semye.android.ui.thirdparty.arouter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.semye.android.R

/**
 *  Created by yesheng on 2020/11/27
 */
@Route(path = "/semye2/route2")
class Router2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router_1)
    }
}