package com.semye.android.utils;

import androidx.annotation.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yesheng on 2017/1/16.
 * md5 在android上的使用
 */
public class MD5Utils {

    private static final String MD5 = "MD5";

    /**
     * 32位MD5
     *
     * @param str str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encode(String str) throws NoSuchAlgorithmException {
        //消息摘要
        MessageDigest messageDigest = MessageDigest.getInstance(MD5);
        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);
        byte[] md5Bytes = messageDigest.digest(byteArray);

        return bytes2Hex(md5Bytes);
    }

    /**
     * 2进制转16进制
     *
     * @param bytes bytes
     * @return
     */
    private static String bytes2Hex(byte[] bytes) {
        StringBuffer result = new StringBuffer(bytes.length * 2);
        int temp;
        for (byte aByte : bytes) {
            temp = aByte;
            if (temp < 0) {
                temp += 256;
            }
            if (temp < 16) {
                result.append("0");
            }
            result.append(Integer.toHexString(temp));
        }

        return result.toString();
    }


    /**
     * 16位MD5
     *
     * @param str str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encode16(@NonNull String str) throws NoSuchAlgorithmException {
        return encode(str).substring(8, 24);
    }

}
