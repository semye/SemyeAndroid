package com.semye.android.ui.thirdparty.eventbus

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class EventBusMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventbus)
        EventBus.getDefault().register(this)

        mButton = findViewById(R.id.btn1);
        mButton.setOnClickListener(this)
    }


    @Subscribe
    fun onEvent(string: String) {
        println("===>$string")
    }


    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                EventBus.getDefault().post("hellloworld")
            }
        }
    }
}