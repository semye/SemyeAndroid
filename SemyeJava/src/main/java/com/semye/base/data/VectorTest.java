package com.semye.base.data;

import java.util.Vector;

/**
 * Created by yesheng on 2020/12/30
 * <p>
 * Vector 线程安全
 *
 * @see java.util.ArrayList
 * 不要求线程安全推荐使用ArrayList代替Vector
 */
class VectorTest {


    public static void main(String[] args) {
        Vector<String> vector = new Vector<>(6);
        vector.addAll(Constants.WOW);
        System.out.println(vector.toString());
        System.out.println(vector.size());
        System.out.println(vector.firstElement());
        System.out.println(vector.lastElement());
        System.out.println(vector.elementAt(5));
        vector.trimToSize();
        System.out.println(vector.hashCode());
        System.out.println(vector.toString());
        Vector<String> vector1 = (Vector<String>) vector.clone();
        vector1.remove(0);
        System.out.println(vector1.hashCode());
        System.out.println(vector1.toString());
    }
}
