package com.semye.android.module.jetpack.nestedscroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 * 子视图实现
 */
class ChildNestScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_nested_scroll)
    }
}