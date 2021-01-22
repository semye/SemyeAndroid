package com.semye.base.encrypt;

import java.util.Base64;

public class Base64Util {

    public static void main(String[] args) {
        String encodeString = Base64.getEncoder().encodeToString("hello".getBytes());
        System.out.println(encodeString);
        String decodeString = new String(Base64.getDecoder().decode(encodeString));
        System.out.println(decodeString);
    }
}
