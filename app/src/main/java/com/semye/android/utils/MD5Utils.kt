package com.semye.android.utils

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by yesheng on 2017/1/16.
 * md5 在android上的使用
 */
object MD5Utils {
    private const val MD5 = "MD5"

    /**
     * 32位MD5
     *
     * @param str str
     * @return
     * @throws NoSuchAlgorithmException
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun encode(str: String): String {
        //消息摘要
        val messageDigest = MessageDigest.getInstance(MD5)
        val byteArray = str.toByteArray(StandardCharsets.UTF_8)
        val md5Bytes = messageDigest.digest(byteArray)
        return bytes2Hex(md5Bytes)
    }

    /**
     * 2进制转16进制
     *
     * @param bytes bytes
     * @return
     */
    private fun bytes2Hex(bytes: ByteArray): String {
        val result = StringBuffer(bytes.size * 2)
        var temp: Int
        for (aByte in bytes) {
            temp = aByte.toInt()
            if (temp < 0) {
                temp += 256
            }
            if (temp < 16) {
                result.append("0")
            }
            result.append(Integer.toHexString(temp))
        }
        return result.toString()
    }

    /**
     * 16位MD5
     *
     * @param str str
     * @return
     * @throws NoSuchAlgorithmException
     */
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun encode16(str: String): String {
        return encode(str).substring(8, 24)
    }
}