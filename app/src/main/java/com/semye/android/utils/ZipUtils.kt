package com.semye.android.utils

import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Created by yesheng on 15/5/20.
 * 文件压缩
 */
object ZipUtils {
    const val TAG = "ZipUtils"
    const val BUFFER_SIZE = 512 * 1024

    /**
     * @param files   文件的大小
     * @param zipFile 文件路径
     * @throws IOException
     */
    @Throws(IOException::class)
    fun zip(files: Array<String>, zipFile: String?) {
        var origin: BufferedInputStream
        val out = ZipOutputStream(BufferedOutputStream(FileOutputStream(zipFile)))
        try {
            val data = ByteArray(BUFFER_SIZE)
            for (i in files.indices) {
                val fi = FileInputStream(files[i])
                origin = BufferedInputStream(fi, BUFFER_SIZE)
                try {
                    val entry = ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1))
                    out.putNextEntry(entry)
                    var count: Int
                    while (origin.read(data, 0, BUFFER_SIZE).also { count = it } != -1) {
                        out.write(data, 0, count)
                    }
                } finally {
                    origin.close()
                }
            }
        } finally {
            out.close()
        }
    }

    /**
     * @param zipFile  解压的文件
     * @param location 解压的路径
     * @throws IOException
     */
    @Throws(IOException::class)
    fun unzip(zipFile: String?, location: String): Boolean {
        var flag = false
        try {
            val data = ByteArray(BUFFER_SIZE)
            val f = File(location)
            if (!f.isDirectory) {
                f.mkdirs()
            }
            val zin = ZipInputStream(FileInputStream(zipFile))
            try {
                var ze: ZipEntry
                while (zin.nextEntry.also { ze = it } != null) {
                    val path = location + ze.name
                    if (ze.isDirectory) {
                        val unzipFile = File(path)
                        if (!unzipFile.isDirectory) {
                            unzipFile.mkdirs()
                        }
                    } else {
                        val fout = FileOutputStream(path, false)
                        try {
                            var c: Int
                            while (zin.read(data).also { c = it } != -1) {
                                fout.write(data, 0, c)
                            }
                            zin.closeEntry()
                        } finally {
                            fout.close()
                            flag = true
                        }
                    }
                }
            } finally {
                zin.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Unzip exception", e)
        }
        return flag
    }
}