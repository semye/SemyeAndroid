package com.semye.android.ui.flutter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
//import io.flutter.embedding.android.FlutterActivity

class FlutterMainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter_main)

        findViewById<View>(R.id.rootview)?.setOnClickListener {
//            startActivity(
//                FlutterActivity
//                    .withCachedEngine("semye")
//                    .build(this)
//            );
        }
    }
}