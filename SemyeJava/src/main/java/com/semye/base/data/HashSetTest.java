package com.semye.base.data;

import java.util.HashSet;

/**
 * Created by semye on 2020/12/16
 * 集合
 * 内部使用hashmap实现
 */
public class HashSetTest {

    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            hashSet.add(String.valueOf(i));
        }
        System.out.println(hashSet.size());
    }
}
