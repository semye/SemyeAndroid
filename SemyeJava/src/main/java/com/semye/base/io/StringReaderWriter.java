package com.semye.base.io;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringReaderWriter {


    public static void main(String[] args) throws IOException {
        stringWriter();
        stringReader();
    }

    public static void stringWriter() throws IOException {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write("hello stringwriter");
        stringWriter.close();
        System.out.println(stringWriter.toString());
    }

    public static void stringReader() throws IOException {
        String abc = "abca bab a ba b";
        StringReader stringReader = new StringReader(abc);

        char[] buff = new char[10];
        int len;
        while ((len = stringReader.read(buff)) != -1) {
            System.out.println(len);
            String str = new String(buff, 0, len);
            System.out.println(str);
        }
        stringReader.close();
    }

}
