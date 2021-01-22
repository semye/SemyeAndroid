package com.semye.base.io;

import java.io.*;

public class FileReaderWriter {

    public static final String FILENAME = "file.txt";

    public static void main(String[] args) throws IOException {
        fileWriter();
        fileReader();
    }

    public static void fileWriter() throws IOException {
        FileWriter fileWriter = new FileWriter(FILENAME);
        fileWriter.write("java io study !,java io study !,java io study !,java io study !");
        fileWriter.close();
    }

    public static void fileReader() throws IOException {
        FileReader fileReader = new FileReader(FILENAME);
        char[] buff = new char[10];
        int len;
        StringBuilder builder = new StringBuilder();
        while ((len = fileReader.read(buff)) != -1) {
            System.out.println(buff);
            System.out.println(len);
            String str = new String(buff, 0, len);
            System.out.println(str);
            builder.append(str);
        }
        fileReader.close();
        System.out.println(builder.toString());
    }
}
