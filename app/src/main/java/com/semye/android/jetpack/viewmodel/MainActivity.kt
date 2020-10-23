package com.semye.android.jetpack.viewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.semye.android.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_viewmodel)
        val textView = findViewById<TextView>(R.id.text)
        val button = findViewById<Button>(R.id.button)

        val userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            UserViewModel::class.java
        )

        button.setOnClickListener { userViewModel.request() }

        userViewModel.user.observe(this, Observer {
            textView.text = it.name
        })
    }
}
