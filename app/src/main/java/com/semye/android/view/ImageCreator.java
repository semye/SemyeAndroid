package com.semye.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import com.semye.android.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yesheng on 2019/4/3
 * 生成图片
 */
public class ImageCreator {

    public static void create(Context context) {
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.dark_header);


        Bitmap bitmap2 = Bitmap.createBitmap(bitmap1, 0, 0, 365, 200);

        if (!bitmap1.isRecycled()) {
            bitmap1.recycle();
        }

        Bitmap bitmapDrawable = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapDrawable);

        canvas.drawColor(Color.WHITE);

        canvas.drawBitmap(bitmap2, 0, 0, new Paint());


        canvas.drawRect(60, 180, 1020, 800, new Paint());


        TextPaint paint = new TextPaint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(42);
        String text = "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的" +
                "我的我的我的我的我的";

        StaticLayout staticLayout = new StaticLayout(text, paint, 960, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);

        canvas.translate(60, 180);

        staticLayout.draw(canvas);

        canvas.translate(0, 0);
        canvas.save();
        canvas.restore();


        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/share_pic.png");
        Log.i("TAG", Environment.getExternalStorageDirectory().getPath());
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            bitmapDrawable.compress(Bitmap.CompressFormat.PNG, 50, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
    }
}
