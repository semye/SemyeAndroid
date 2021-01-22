package com.semye.base.io;

import java.io.*;

public class IoTest {


    //字符流 Reader Writer

    //字节流 InputStream OutputStream

    private static final String PATH = "file.txt";

    public static void main(String[] args) {


    }

    public static void read() {
        File f = new File(PATH);
        int i = (int) f.length();
        try {

            FileWriter fw = new FileWriter(f);
            fw.write("zzzzzzzzzzzzzzzzzzzzzzzz");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            FileReader fr = new FileReader(f);
            char[] c = new char[i];
            int length = fr.read(c);
            String s = new String(c, 0, length);
            System.out.println(s);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hello() {

        File f = new File(PATH);
        int i = (int) f.length();

        try {
            FileOutputStream fos = new FileOutputStream(f);
            String ss = "dasdasdweqweqwrdasdadasd";
            fos.write(ss.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(f);
            byte[] b = new byte[i];
            while (fis.read(b) != -1) {
                System.out.println(new String(b));
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
