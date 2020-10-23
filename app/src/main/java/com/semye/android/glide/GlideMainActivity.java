package com.semye.android.glide;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.semye.android.R;

public class GlideMainActivity extends AppCompatActivity {

    public static final String TAG = GlideMainActivity.class.getSimpleName();

    public static final String LOCAL_IMAGE_URL = "file:///android_asset/8cb1cb134954092341ce53589258d109b3de4974.jpg";
    public static final String REMOTE_IMAGE_URL = "http://pic.nipic.com/2008-05-06/200856201542395_2.jpg";
    public static final String REMOTE_GIF_IMAGE_URL = "https://i0.hdslb.com/bfs/sycp/creative_img/201901/f11877f739973438c3b47ecfc91a1577.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide);
        final ImageView imageView = findViewById(R.id.image);

        final LinearLayout linearLayout = findViewById(R.id.ll_bg);

        Button local = findViewById(R.id.btn_load_local);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加载本地图片
                Uri uri = Uri.parse(LOCAL_IMAGE_URL);
//                GlideApp.with(GlideMainActivity.this)
//                        .load(uri)
//                        .into(imageView);
            }
        });

        Button net = findViewById(R.id.btn_load_network);
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加载网络图片
                //添加header
                GlideUrl glideUrl = new GlideUrl(REMOTE_IMAGE_URL, new LazyHeaders.Builder()
                        .addHeader("ye", "sheng")
                        .build());
//                GlideApp.with(GlideMainActivity.this)
//                        .load(glideUrl)
//                        .into(imageView);
            }
        });

        Button gifCorner = findViewById(R.id.btn_gif);
        gifCorner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GlideApp.with(GlideMainActivity.this).
//                        setRequestOptions(new RequestOptions().transforms(new RoundedCorners(8)));
//                GlideApp.with(GlideMainActivity.this)
//                        .load(REMOTE_GIF_IMAGE_URL)
//                        .into(imageView);
            }
        });
        Button bitmapButton = findViewById(R.id.btn_bitmap);
        bitmapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GlideApp.with(GlideMainActivity.this)
//                        强制加载静态图
//                        .asBitmap()
//                        .load(REMOTE_GIF_IMAGE_URL)
//                        .into(imageView);
            }
        });

        Button bgWhite = findViewById(R.id.btn_bg_white);
        bgWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setBackgroundColor(Color.WHITE);
            }
        });

        Button bgBlack = findViewById(R.id.btn_bg_black);
        bgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setBackgroundColor(Color.BLACK);
            }
        });

    }

}
