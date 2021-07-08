package com.semye.android.utils

import android.os.Environment
import java.io.*

/**
 * Created by yesheng on 2017/1/10.
 * android文件管理类
 */
object FileUtils {
    private val fileManager: FileUtils? = null

    /**
     * 获取SD卡的根目录
     *
     * @return 如果SD卡存在, 返回SD卡的根目录,否则返回null
     */
    val sDCardRootPath: String
        get() {
            if (!isSDcardExist) try {
                throw Exception("SD卡不存在")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Environment.getExternalStorageDirectory().path
        }

    /**
     * 判断SDcard是否存在
     *
     * @return 如果存在返回true 如果不存在返回false
     */
    val isSDcardExist: Boolean
        get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    fun rename(fileName: String, newName: String): String {
        var fileName = fileName
        if (fileName.contains(".")) {
            val index = fileName.lastIndexOf(".")
            fileName = newName + fileName.substring(index, fileName.length)
            return fileName
        }
        return newName
    }

    fun createFolder(folderName: String?): Boolean {
        val path = Environment.getExternalStorageDirectory().path
        val file = File(folderName)
        return if (!file.exists()) {
            file.mkdir()
        } else false
    }

    /**
     * 检测SD卡是否存在
     */
    fun checkSDcard(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment
                .getExternalStorageState()
    }

    /**
     * 获取文件保存点
     */
    fun getSaveFile(fileNmae: String): File? {
        var file: File? = null
        try {
            file = File(Environment.getExternalStorageDirectory()
                    .canonicalFile.toString() + "/" + fileNmae)
        } catch (e: IOException) {
        }
        return file
    }

    /**
     * 从指定文件夹获取文件
     */
    fun getSaveFile(folder: String, fileNmae: String?): File {
        return File(getSavePath(folder), fileNmae)
    }

    /**
     * 获取文件保存路径
     */
    fun getSavePath(folder: String): String {
        return Environment.getExternalStorageDirectory().toString() + "/" + folder
    }

    @Throws(IOException::class)
    fun split(file: File) {
        val dir = File("/sdcard/xckyapp/pices/")
        dir.mkdir()

        // 计算每一份的大小
        val partLen = (file.length() + 4) / 5 // (10 + 4) / 5 = 2, (14 + 4) /
        // 5 = 3
        var fileNum = 1
        var len: Long = 0
        val bis = BufferedInputStream(FileInputStream(
                file))
        var bos = BufferedOutputStream(
                FileOutputStream(File(dir, "$fileNum.temp")))
        var b: Int
        while (bis.read().also { b = it } != -1) {
            if (len++ == partLen) {
                bos.close()
                bos = BufferedOutputStream(FileOutputStream(File(
                        dir, (++fileNum).toString() + ".temp")))
                len = 0
            }
            bos.write(b)
        }
        bis.close()
        bos.close()
    }
}