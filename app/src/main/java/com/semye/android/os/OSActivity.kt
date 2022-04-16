package com.semye.android.os

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class OSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_os)
        val infoView = findViewById<TextView>(R.id.info)

        val metrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.getMetrics(metrics)
        } else {
            windowManager.defaultDisplay.getMetrics(metrics)
        }

        val info =
            "屏幕宽:" + metrics.widthPixels + "\n屏幕高:" + metrics.heightPixels + "\ndpi:" + metrics.densityDpi + "" +
                    "\n屏幕密度:" + metrics.density + "\nx:" + metrics.xdpi + "\ny:" + metrics.ydpi +
                    "\n1dp 等于" + TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1f,
                metrics
            ) + "像素" +
                    "\n1sp 等于" + TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 1f,
                metrics
            ) + "像素"


        infoView.text = info
    }
}