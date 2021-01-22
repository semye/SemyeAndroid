package com.semye.android.thirdparty.dragger2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.semye.android.R;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_SDCARD_PERMISSION = 2;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    Item model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.builder().build().inject(this);
        checkPermissions();


        model.println();
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "当前手机系统版本大于6.0");
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "当前手机没有读写SD卡的权限,去获取");
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_SDCARD_PERMISSION);
            } else {
                Log.d(TAG, "当前手机有读写SD卡的权限");
            }
        } else {
            Log.d(TAG, "当前手机有读写SD卡的权限");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_SDCARD_PERMISSION:
                if (grantResults.length != 0) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "获取SD卡读写权限失败");
                        Toast.makeText(this, "获取手机SD卡读写权限失败,请在设置中打开相关权限", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "获取SD卡读写权限成功！");
                    }
                } else {
                    Log.d(TAG, "获取SD卡读写权限失败");
                }
                break;
        }
    }
}
