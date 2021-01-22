package com.semye.base.io;

import java.io.*;

/**
 * @see java.io.Reader 字符读取
 * @see java.io.Writer 字符写入
 * @see java.io.InputStream 字节读取
 * @see java.io.OutputStream 字节写入
 */
public class Main {


    public static void main(String[] args) throws IOException {
        pathname();
//        System.out.println(System.currentTimeMillis());
//        FileOutputStream fileOutputStream = new FileOutputStream("b");
//        fileOutputStream.write(new byte[]{1, 2, 3, 4, 'A'});
//        fileOutputStream.close();
//        FileInputStream fileInputStream = new FileInputStream("a.txt");


    }

    /**
     * file 构造方法 pathname
     * 根据路径名创建一个file对象
     */
    public static void pathname() {
        File file = new File("my file");
        System.out.println("file not null");
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println("====>" + file.toString());
    }
}
