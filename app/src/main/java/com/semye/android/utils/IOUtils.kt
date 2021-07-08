package com.semye.android.utils

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by yesheng on 2017/1/16.
 */
object IOUtils {
    @Throws(IOException::class)
    fun toByteArray(input: InputStream): ByteArray {
        val output = ByteArrayOutputStream()
        copy(input, output)
        return output.toByteArray()
    }

    @Throws(IOException::class)
    fun copy(input: InputStream, output: OutputStream): Int {
        val count = copyLarge(input, output)
        return if (count > Int.MAX_VALUE) {
            -1
        } else count.toInt()
    }

    @Throws(IOException::class)
    fun copyLarge(input: InputStream, output: OutputStream): Long {
        val buffer = ByteArray(4096)
        var count: Long = 0
        var n = 0
        while (-1 != input.read(buffer).also { n = it }) {
            output.write(buffer, 0, n)
            count += n.toLong()
        }
        return count
    }
}