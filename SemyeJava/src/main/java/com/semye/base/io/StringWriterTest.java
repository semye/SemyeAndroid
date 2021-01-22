package com.semye.base.io;


import java.io.IOException;
import java.io.StringWriter;

public class StringWriterTest {

    StringWriter stringWriter;

    public void setUp() throws Exception {
        stringWriter = new StringWriter();
    }

    public void tearDown() throws Exception {
        stringWriter = null;
    }

    public void write() {
        stringWriter = new StringWriter();
        stringWriter.write("hello");
    }

    public void write1() {
        stringWriter = new StringWriter();
        stringWriter.write('c');
    }

    public void write2() {
        stringWriter = new StringWriter();
        stringWriter.write("sssdsadasda", 1, 4);
    }

    public void write3() throws IOException {
        stringWriter = new StringWriter();
        stringWriter.write(new char[]{'a', 'c'});
    }

    public void write4() {
        stringWriter = new StringWriter();
        stringWriter.write(new char[]{'a', 'c'}, 0, 1);
    }

    public void append() {
        stringWriter = new StringWriter();
        stringWriter.write("hello");
        stringWriter.append("yesheng");
    }

    public void append1() {
        stringWriter = new StringWriter();
        stringWriter.write("hello");
        stringWriter.append('A');
    }

    public void append2() {
        stringWriter = new StringWriter();
        stringWriter.write("hello");
        stringWriter.append("1234567890", 0, 1);
    }

}
