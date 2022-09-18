package com.semye.android.thirdparty.dragger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.thirdparty.dragger2.component.DaggerMineComponent

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMineComponent.builder().build().inject(this)
    }
}