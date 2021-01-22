package com.semye.base.encrypt;


public class AESCryptTest {

    public void testAll() throws Exception {
        String yesheng = "yesheng3";
        String cryptText = AESCrypt.encrypt(yesheng);
        System.out.println(cryptText);
        String plainText = AESCrypt.decrypt(cryptText);
        System.out.println(plainText);
    }
}
