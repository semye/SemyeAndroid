package com.semye.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class WindowMainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        Window window = getWindow();
        System.out.println("window" + window.toString());
        findViewById(R.id.add_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowMainActivity.this.addView1();
                WindowMainActivity.this.addView2();
                WindowMainActivity.this.addView3();
                WindowMainActivity.this.addView4();
            }
        });

        findViewById(R.id.remove_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (textView1 != null && textView1.isAttachedToWindow()) {
                        WindowMainActivity.this.getWindowManager().removeView(textView1);
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (textView2 != null && textView2.isAttachedToWindow()) {
                        WindowMainActivity.this.getWindowManager().removeView(textView2);
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (textView3 != null && textView3.isAttachedToWindow()) {
                        WindowMainActivity.this.getWindowManager().removeView(textView3);
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (textView4 != null && textView4.isAttachedToWindow()) {
                        WindowMainActivity.this.getWindowManager().removeView(textView4);
                    }
                }
            }
        });
    }

    @SuppressLint("RtlHardcoded")
    private void addView1() {
        WindowManager windowManager = getWindowManager();
        System.out.println("WindowManager  " + windowManager.toString());
        textView1 = new TextView(this);
        textView1.setText("左上角");
        textView1.setTextColor(Color.WHITE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.x = 0;
        layoutParams.y = 0;
        windowManager.addView(textView1, layoutParams);
    }

    @SuppressLint("RtlHardcoded")
    private void addView2() {
        WindowManager windowManager = getWindowManager();
        System.out.println("WindowManager  " + windowManager.toString());
        textView2 = new TextView(this);
        textView2.setText("右上角");
        textView2.setTextColor(Color.WHITE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.RIGHT | Gravity.TOP;
        layoutParams.x = 0;
        layoutParams.y = 0;
        windowManager.addView(textView2, layoutParams);
    }

    @SuppressLint("RtlHardcoded")
    private void addView3() {
        WindowManager windowManager = getWindowManager();
        System.out.println("WindowManager  " + windowManager.toString());
        textView3 = new TextView(this);
        textView3.setText("右下角");
        textView3.setTextColor(Color.WHITE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.setTitle("helloworld");
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        layoutParams.x = 0;
        layoutParams.y = 0;
        windowManager.addView(textView3, layoutParams);
    }

    @SuppressLint("RtlHardcoded")
    private void addView4() {
        WindowManager windowManager = getWindowManager();
        System.out.println("WindowManager  " + windowManager.toString());
        textView4 = new TextView(this);
        textView4.setText("左下角");
        textView4.setTextColor(Color.WHITE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
        layoutParams.x = 0;
        layoutParams.y = 0;
        windowManager.addView(textView4, layoutParams);
    }
}
