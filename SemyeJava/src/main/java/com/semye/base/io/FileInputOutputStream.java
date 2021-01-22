package com.semye.base.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 输入输出流
 */
public class FileInputOutputStream {

    public static void main(String[] args) throws IOException {
        fileOutputStream();
        fileInputStream();
    }

    public static void fileInputStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("file.txt");
    }

    public static void fileOutputStream() throws IOException {
        String aa = "hhhdasfgdhfjggdfsdgsfdgs";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("file.txt");
            fileOutputStream.write(aa.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
