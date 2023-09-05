package com.semye.android.module.item22_actionbar

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.semye.android.R

class ActionBarMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actionbar)

        val switch1 = findViewById<SwitchCompat>(R.id.switch1)
        switch1?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                supportActionBar?.setDisplayShowTitleEnabled(true)
            } else {
                supportActionBar?.setDisplayShowTitleEnabled(false)
            }
        }

        val switch2 = findViewById<SwitchCompat>(R.id.switch2)
        switch2?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        val switch3 = findViewById<SwitchCompat>(R.id.switch3)
        switch3?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                supportActionBar?.setDisplayShowHomeEnabled(true)
            } else {
                supportActionBar?.setDisplayShowHomeEnabled(false)
            }
        }

        val switch4 = findViewById<SwitchCompat>(R.id.switch4)
        switch4?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                supportActionBar?.setDisplayUseLogoEnabled(true)
            } else {
                supportActionBar?.setDisplayUseLogoEnabled(false)
            }
        }

        val switch5 = findViewById<SwitchCompat>(R.id.switch5)
        switch5?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                supportActionBar?.show()
            } else {
                supportActionBar?.hide()
            }
        }

        val switch6 = findViewById<SwitchCompat>(R.id.switch6)
        switch6?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                supportActionBar?.setDisplayShowCustomEnabled(true)
                val textView = TextView(this)
                textView.gravity = Gravity.CENTER
                textView.text = "自定义view"
                supportActionBar?.setCustomView(
                    textView,
                    ActionBar.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.MATCH_PARENT
                    )
                )
            } else {
                supportActionBar?.setDisplayShowCustomEnabled(false)
            }
        }

        //是否可以显示标题和子标题
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //是否可以显示返回箭头
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(false)

        supportActionBar?.title = "标题"
        supportActionBar?.subtitle = "子标题"
        //icon和logo的区别  logo可以通过setDisplayUseLogoEnabled显示隐藏 icon不行
        // icon只能通过setDisplayShowHomeEnabled来显示隐藏
        supportActionBar?.setLogo(R.mipmap.ic_launcher) //设置home位置的logo
        supportActionBar?.setIcon(R.mipmap.ic_launcher) //设置home位置的icon

        //设置HomeAsUp的资源
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_text)
    }
}
