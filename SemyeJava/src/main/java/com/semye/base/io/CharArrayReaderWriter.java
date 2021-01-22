package com.semye.base.io;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class CharArrayReaderWriter {

    public static void main(String[] args) throws IOException {
        charArrayWriter();
        charArrayReader();
    }

    public static void charArrayReader() throws IOException {
        String helloworld = "helloworld!";
        CharArrayReader charArrayReader = new CharArrayReader(helloworld.toCharArray());
        int len;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len = charArrayReader.read()) != -1) {
            stringBuilder.append((char) len);
        }
        System.out.println(stringBuilder.toString());
        charArrayReader.close();
    }

    public static void charArrayWriter() throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        charArrayWriter.write("helloworld!");
        charArrayWriter.close();
        System.out.println(charArrayWriter.toCharArray());
    }
}

