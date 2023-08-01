package com.semye.android.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.*
import java.io.ByteArrayOutputStream

/**
 * Created by yesheng on 15/10/28.
 */
object BitmapUtils {
    /**
     * Get bitmap from specified image path
     *
     * @param imgPath
     * @return
     */
    @JvmStatic
    fun getBitmap(imgPath: String): Bitmap? {
        try {
            // Get bitmap through image path
            val newOpts = BitmapFactory.Options()
            newOpts.inJustDecodeBounds = false
            newOpts.inPurgeable = true
            newOpts.inInputShareable = true
            // Do not compress
            newOpts.inSampleSize = 1
            newOpts.inPreferredConfig = Bitmap.Config.RGB_565
            return BitmapFactory.decodeFile(imgPath, newOpts)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Store bitmap into specified image path
     *
     * @param bitmap
     * @param outPath
     * @throws FileNotFoundException
     */
    @Throws(FileNotFoundException::class)
    fun storeImage(bitmap: Bitmap, outPath: String?) {
        val os = FileOutputStream(outPath)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
    }

    /**
     * Compress image by pixel, this will modify image width/height.
     * Used to get thumbnail
     *
     * @param imgPath image path
     * @param pixelW  target pixel of width
     * @param pixelH  target pixel of height
     * @return
     */
    fun ratio(imgPath: String?, pixelW: Float, pixelH: Float): Bitmap {
        val newOpts = BitmapFactory.Options()
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true，即只读边不读内容
        newOpts.inJustDecodeBounds = true
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565
        // Get bitmap info, but notice that bitmap is null now
        var bitmap = BitmapFactory.decodeFile(imgPath, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        // 想要缩放的目标尺寸
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1 //be=1表示不缩放
        // 设置宽度为120f，可以明显看到图片缩小了
        if (w > h && w > pixelW) { //如果宽度大的话根据宽度固定大小缩放
            // 设置宽度为120f，可以明显看到图片缩小了
            be = (newOpts.outWidth / pixelW).toInt()
        } else if (w < h && h > pixelH // 设置高度为240f时，可以明显看到图片缩小了
        ) { //如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / pixelH // 设置高度为240f时，可以明显看到图片缩小了
                    ).toInt()
        }
        if (be <= 0) be = 1
        newOpts.inSampleSize = be //设置缩放比例
        // 开始压缩图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(imgPath, newOpts)
        // 压缩好比例大小后再进行质量压缩
        //        return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap
    }

    /**
     * Compress image by size, this will modify image width/height.
     * Used to get thumbnail
     *
     * @param image
     * @param pixelW target pixel of width
     * @param pixelH target pixel of height
     * @return
     */
    fun ratio(image: Bitmap, pixelW: Float, pixelH: Float): Bitmap? {
        val os = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, os)
        if (os.toByteArray().size / 1024 > 1024) { //判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            os.reset() //重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, os) //这里压缩50%，把压缩后的数据存放到baos中
        }
        var `is` = ByteArrayInputStream(os.toByteArray())
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565
        var bitmap = BitmapFactory.decodeStream(`is`, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1 //be=1表示不缩放
        // 设置宽度为120f，可以明显看到图片缩小了
        if (w > h && w > pixelW) { //如果宽度大的话根据宽度固定大小缩放
            // 设置宽度为120f，可以明显看到图片缩小了
            be = (newOpts.outWidth / pixelW).toInt()
        } else if (w < h && h > pixelH // 设置高度为240f时，可以明显看到图片缩小了
        ) { //如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / pixelH // 设置高度为240f时，可以明显看到图片缩小了
                    ).toInt()
        }
        if (be <= 0) be = 1
        newOpts.inSampleSize = be //设置缩放比例
//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        `is` = ByteArrayInputStream(os.toByteArray())
        bitmap = BitmapFactory.decodeStream(`is`, null, newOpts)
//压缩好比例大小后再进行质量压缩
//      return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bitmap
    }

    /**
     * Compress by quality,  and generate image to the path specified
     *
     * @param image
     * @param outPath
     * @param maxSize target will be compressed to be smaller than this size.(kb)
     * @throws IOException
     */
    @Throws(IOException::class)
    fun compressAndGenImage(image: Bitmap, outPath: String?, maxSize: Int) {
        val os = ByteArrayOutputStream()
        // scale
        var options = 100
        // Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os)
        // Compress by loop
        while (os.toByteArray().size / 1024 > maxSize) {
            // Clean up os
            os.reset()
            // interval 10
            options -= 10
            image.compress(Bitmap.CompressFormat.JPEG, options, os)
        }

        // Generate compressed image file
        val fos = FileOutputStream(outPath)
        fos.write(os.toByteArray())
        fos.flush()
        fos.close()
    }

    /**
     * Compress by quality,  and generate image to the path specified
     *
     * @param imgPath
     * @param outPath
     * @param maxSize     target will be compressed to be smaller than this size.(kb)
     * @param needsDelete Whether delete original file after compress
     * @throws IOException
     */
    @Throws(IOException::class)
    fun compressAndGenImage(
            imgPath: String?,
            outPath: String?,
            maxSize: Int,
            needsDelete: Boolean
    ) {
        if (imgPath == null) return
        val bitmap = getBitmap(imgPath) ?: return
        compressAndGenImage(bitmap, outPath, maxSize)

        // Delete original file
        if (needsDelete) {
            val file = File(imgPath)
            if (file.exists()) {
                file.delete()
            }
        }
    }

    /**
     * Ratio and generate thumb to the path specified
     *
     * @param image
     * @param outPath
     * @param pixelW  target pixel of width
     * @param pixelH  target pixel of height
     * @throws FileNotFoundException
     */
    @Throws(FileNotFoundException::class)
    fun ratioAndGenThumb(image: Bitmap, outPath: String?, pixelW: Float, pixelH: Float) {
        val bitmap = ratio(image, pixelW, pixelH)
        bitmap?.let {
            storeImage(it, outPath)
        }
    }

    /**
     * Ratio and generate thumb to the path specified
     *
     * @param outPath
     * @param pixelW      target pixel of width
     * @param pixelH      target pixel of height
     * @param needsDelete Whether delete original file after compress
     * @throws FileNotFoundException
     */
    @Throws(FileNotFoundException::class)
    fun ratioAndGenThumb(
            imgPath: String?,
            outPath: String?,
            pixelW: Float,
            pixelH: Float,
            needsDelete: Boolean
    ) {
        val bitmap = ratio(imgPath, pixelW, pixelH)
        storeImage(bitmap, outPath)

        // Delete original file
        if (needsDelete) {
            val file = File(imgPath)
            if (file.exists()) {
                file.delete()
            }
        }
    }


    /**
     * Bitmap → Drawable
     *
     * @param bitmap bitmap
     * @return drawable
     */
    fun convertBitmap2Drawable(bitmap: Bitmap?): Drawable {
        return BitmapDrawable(null, bitmap)
    }

    fun compressQuality(imagePath: String?, file: File?): Bitmap? {
        val baos = ByteArrayOutputStream()
        //指定缩略图
        val bitmaps = BitmapFactory.decodeFile(imagePath)
        if (bitmaps != null) {
            bitmaps.compress(Bitmap.CompressFormat.JPEG, 85, baos)
            return bitmaps
        }
        return null
    }

    fun transImage(fromFile: String?, toFile: String?) {
        try {
            val bitmap = BitmapFactory.decodeFile(fromFile)
            val bitmapWidth = bitmap.width
            val bitmapHeight = bitmap.height
            val ratio = Math.sqrt(1100000.toDouble() / (bitmapWidth * bitmapHeight)).toFloat()
            // 缩放图片的尺寸
            val scaleWidth = ratio * bitmapWidth
            val scaleHeight = ratio * bitmapHeight
            val w = scaleWidth / bitmapWidth.toFloat()
            val h = scaleHeight / bitmapHeight.toFloat()
            val matrix = Matrix()
            matrix.postScale(w, h)
            // 产生缩放后的Bitmap对象
            val resizeBitmap =
                    Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false)
            // save file
            val myCaptureFile = File(toFile)
            val out = FileOutputStream(myCaptureFile)
            if (resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)) {
                out.flush()
                out.close()
            }
            if (!bitmap.isRecycled) {
                bitmap.recycle() //记得释放资源，否则会内存溢出
            }
            if (!resizeBitmap.isRecycled) {
                resizeBitmap.recycle()
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    const val SUFFIX = "png"

    /**
     * 替换图片后缀名
     *
     * @param fileName 图片名
     * @return
     */
    fun switchSuffix(fileName: String): String {
        return fileName.substring(0, fileName.indexOf(".")) + "-1" + SUFFIX
    }

}