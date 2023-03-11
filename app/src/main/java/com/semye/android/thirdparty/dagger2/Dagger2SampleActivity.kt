package com.semye.android.thirdparty.dagger2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.semye.android.R
import com.semye.android.thirdparty.dagger2.component.DaggerMainComponent
import com.semye.android.thirdparty.dagger2.model.MyCustom
import okhttp3.OkHttpClient
import javax.inject.Inject

class Dagger2SampleActivity : AppCompatActivity() {

    @JvmField
    @Inject
    var gson: Gson? = null

    @JvmField
    @Inject
    var okHttpClient: OkHttpClient? = null

    @JvmField
    @Inject
    var myCustom: MyCustom? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainComponent.builder()
            .build().inject(this)
        checkPermissions()
        Log.d(TAG, gson.toString())
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "当前手机系统版本大于6.0")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "当前手机没有读写SD卡的权限,去获取")
                ActivityCompat.requestPermissions(
                    this@Dagger2SampleActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_SDCARD_PERMISSION
                )
            } else {
                Log.d(TAG, "当前手机有读写SD卡的权限")
            }
        } else {
            Log.d(TAG, "当前手机有读写SD卡的权限")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_SDCARD_PERMISSION -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "获取SD卡读写权限失败")
                    Toast.makeText(this, "获取手机SD卡读写权限失败,请在设置中打开相关权限", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(TAG, "获取SD卡读写权限成功！")
                }
            } else {
                Log.d(TAG, "获取SD卡读写权限失败")
            }
        }
    }

    companion object {
        const val REQUEST_SDCARD_PERMISSION = 2
        const val TAG = "Dagger2SampleActivity"
    }
}