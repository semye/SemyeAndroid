package com.semye.android.thirdparty.dragger2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.semye.android.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @JvmField
    @Inject
    var model: Item? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainComponent.builder().build().inject(this)
        checkPermissions()
        model!!.println()
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
                    this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
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
            REQUEST_SDCARD_PERMISSION -> if (grantResults.size != 0) {
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
        val TAG = MainActivity::class.java.simpleName
    }
}