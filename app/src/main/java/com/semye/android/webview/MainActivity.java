package com.semye.android.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.semye.android.R;

/**
 * Created by yesheng on 2019/1/29
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatEditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_url);
        editText.setText("https://www.bilibili.com");


        AppCompatButton button1 = findViewById(R.id.button1);
        AppCompatButton button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.button1:
                openCustomWeb(editText.getText().toString());
                break;
            case R.id.button2:
                openSystemWeb(editText.getText().toString());
                break;
        }

    }

    private void openCustomWeb(String url) {
        Intent intent = new Intent(this, SampleWebActivity.class);
        intent.putExtra(SampleWebActivity.LOAD_URL, url);
        startActivity(intent);
    }

    private void openSystemWeb(String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
