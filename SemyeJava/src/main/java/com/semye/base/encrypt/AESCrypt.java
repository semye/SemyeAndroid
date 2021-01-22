package com.semye.base.encrypt;


import com.google.common.io.BaseEncoding;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by yesheng on 19/12/25.
 * AES加密解密
 * 依赖google guava base64
 * <p>
 * 加密模式  ECB CBC CTR OFB CFB
 * 填充  pkcs5padding  pkcs7padding zeropadding  iso10126  ansix923
 * 数据块  128 192 256
 * 密码
 * 偏移量
 * 输出 base64 hex
 * 字符集 utf-8 gbk gb2312
 */
public class AESCrypt {
    public static final String SEED_16_CHARACTER = "HYECHOwdzj919.Zc";

    private AESCrypt() {
    }

    private static Cipher createCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    private static SecretKeySpec createSecretKeySpec() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(SEED_16_CHARACTER.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = new byte[32];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        return new SecretKeySpec(keyBytes, "AES");
    }

    private static IvParameterSpec createIvParameterSpec() {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String plainText) throws Exception {
        Cipher cipher = createCipher();
        SecretKey key = createSecretKeySpec();
        AlgorithmParameterSpec spec = createIvParameterSpec();
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return BaseEncoding.base64().encode(encrypted);
    }

    public static String decrypt(String cryptText) throws Exception {
        Cipher cipher = createCipher();
        SecretKey key = createSecretKeySpec();
        AlgorithmParameterSpec spec = createIvParameterSpec();
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] bytes = BaseEncoding.base64().decode(cryptText);
        byte[] decrypted = cipher.doFinal(bytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}