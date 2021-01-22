package com.semye.base.io;

import java.io.*;

public class BufferedReaderWriter {

    public static void main(String[] args) throws IOException {
        bufferedWriter();
        bufferedReader();
    }

    public static void bufferedReader() throws IOException {
        FileReader fileReader = new FileReader(FileReaderWriter.FILENAME);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        char[] buff = new char[10];
        int len;
        StringBuilder builder = new StringBuilder();
        while ((len = bufferedReader.read(buff)) != -1) {
            System.out.println(len);
            String str = new String(buff, 0, len);
            System.out.println(str);
            builder.append(str);
        }
        System.out.println(builder.toString());
    }

    public static void bufferedWriter() throws IOException {
        FileWriter fileWriter = new FileWriter(FileReaderWriter.FILENAME);
        BufferedWriter bufferedReader = new BufferedWriter(fileWriter);
        bufferedReader.write("heeeeeeeeeeeeeeee");
        bufferedReader.close();
    }
}
