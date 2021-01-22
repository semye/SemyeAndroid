package com.semye.base.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by semye on 2018/7/31.
 */
public class MessageDigestSample {


    //MessageDigest 消息摘要
    //Message Digest Algorithm  消息摘要算法  SHA-1   SHA-256


    public static void main(String[] args) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update("abc".getBytes("utf-8"));
            byte[] digest = messageDigest.digest();

            System.out.println(new String(digest, "utf-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
