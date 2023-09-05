package com.semye.android.module.item26_viewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.semye.android.R

class ViewModelMainActivity : AppCompatActivity() {

    val biz = MyBiz()

    init {
        lifecycle.addObserver(biz)
    }

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_viewmodel)
        val textView = findViewById<TextView>(R.id.text)
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        button.setOnClickListener { userViewModel.request() }
        button2.setOnClickListener {
            userViewModel.user.value = User().apply {
                name = "叶盛"
            }
        }

        userViewModel.user.observe(this, Observer {
            textView.text = it.name
        })
    }
}
