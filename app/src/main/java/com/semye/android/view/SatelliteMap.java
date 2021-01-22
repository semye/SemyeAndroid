package com.semye.android.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.semye.android.R;


/**
 * Time:12/11/20 4:09 PM
 *
 * @author zhengchengyu
 * <p>
 * Description:
 */
public class SatelliteMap extends View {
    // 卫星图
    private Bitmap satelliteBitmap;
    /**
     * 卫星图背景
     */
    private Bitmap compassBitmap;
    private Bitmap resizeBitmap;
    /**
     * 背景画笔对象的引用
     */
    private Paint bgPaint;

    private int cx = 0;
    private int cy = 0;
    private int compassRadius = 920 / 2;
    private Matrix matrix;

    /**
     * 在java代码里new的时候会用到
     *
     * @param context
     */
    public SatelliteMap(Context context) {
        super(context);
    }

    /**
     * 在xml布局文件中使用时自动调用
     *
     * @param context
     */
    public SatelliteMap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 不会自动调用，如果有默认style时，在第二个构造函数中调用
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SatelliteMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //在View的构造方法中通过TypedArray获取
        Resources res = context.getResources();
        compassBitmap = BitmapFactory.decodeResource(res,
                R.drawable.icon_satellite_bg);
        compassRadius = compassBitmap.getWidth() / 2;
        matrix = new Matrix();
        bgPaint = new Paint();
    }

    /**
     * 只有在API版本>21时才会用到
     * 不会自动调用，如果有默认style时，在第二个构造函数中调用
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SatelliteMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            if (resizeBitmap == null) {
                float wscale = (float) getWidth() / (float) compassBitmap.getWidth();
                float hscale = (float) getHeight() / (float) compassBitmap.getHeight();
                matrix.setScale(wscale, hscale);
                resizeBitmap = Bitmap.createBitmap(compassBitmap, 0, 0, compassBitmap.getWidth(), compassBitmap.getHeight(), matrix, true);
            }
            if (resizeBitmap.isRecycled()) return;
            canvas.drawBitmap(resizeBitmap, 0, 0, bgPaint);
        }
    }
}
