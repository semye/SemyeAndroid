package com.semye.base.io;


import java.io.IOException;
import java.io.StringReader;

public class StringReaderTest {

    StringReader stringReader;

    public void setUp() throws Exception {
        stringReader = new StringReader("ahello");
    }

    public void read() throws IOException {
        //返回首字母的ASCII码
    }
}
