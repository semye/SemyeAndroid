package com.semye.android.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

/**
 *  Created by yesheng on 2020/11/12
 *  这个Activity是用来做跳转的
 */
class TokenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_token)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        Handler().postDelayed(
            {
                val displayMetrics = DisplayMetrics()
                display?.getRealMetrics(displayMetrics)


                Log.d(
                    "yesheng",
                    "onCreate " + "width:" + displayMetrics.widthPixels + " height:" + displayMetrics.heightPixels
                )
            }, 1000
        )


    }


    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        display?.getRealMetrics(displayMetrics)
        Log.d(
            "yesheng",
            "onStart " + "width:" + displayMetrics.widthPixels + " height:" + displayMetrics.heightPixels
        )

    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        display?.getRealMetrics(displayMetrics)
        Log.d(
            "yesheng",
            "onResume " + "width:" + displayMetrics.widthPixels + " height:" + displayMetrics.heightPixels
        )

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        val displayMetrics = DisplayMetrics()
        display?.getRealMetrics(displayMetrics)
        Log.d(
            "yesheng",
            "onConfigurationChanged " + "width:" + displayMetrics.widthPixels + " height:" + displayMetrics.heightPixels
        )

    }
}