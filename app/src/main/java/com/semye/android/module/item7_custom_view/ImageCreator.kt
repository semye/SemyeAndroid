package com.semye.android.module.item7_custom_view

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Environment
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by yesheng on 2019/4/3
 * 生成图片
 */
object ImageCreator {
    fun create(context: Context) {
        val bitmap1 = BitmapFactory.decodeResource(context.resources, R.drawable.dark_header)
        val bitmap2 = Bitmap.createBitmap(bitmap1, 0, 0, 365, 200)
        if (!bitmap1.isRecycled) {
            bitmap1.recycle()
        }
        val bitmapDrawable = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmapDrawable)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap2, 0f, 0f, Paint())
        canvas.drawRect(60f, 180f, 1020f, 800f, Paint())
        val paint = TextPaint()
        paint.color = Color.WHITE
        paint.textSize = 42f
        val text = "我的我的我的我的我的" +
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
            "我的我的我的我的我的"
        val staticLayout =
            StaticLayout(text, paint, 960, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true)
        canvas.translate(60f, 180f)
        staticLayout.draw(canvas)
        canvas.translate(0f, 0f)
        canvas.save()
        canvas.restore()
        val file = File(Environment.getExternalStorageDirectory().path + "/share_pic.png")
        Log.i("TAG", Environment.getExternalStorageDirectory().path)
        val fos: FileOutputStream
        try {
            fos = FileOutputStream(file)
            bitmapDrawable.compress(Bitmap.CompressFormat.PNG, 50, fos)
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (!bitmap2.isRecycled) {
            bitmap2.recycle()
        }
    }
}