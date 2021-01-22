package com.semye.base.target;

/**
 * 这个类是用来反射的
 */
@TargetAnnotation(name = "targetA")
class TargetA implements TargetInterface<String> {
    public String field1;
    String field2;
    private String field3;
    protected String field4;

    public void method1(String a, String b) {

    }

    void method2() {

    }

    private void method3() {

    }

    protected void method4() {

    }

    class InnerTarget {

    }

    public class InnerTargetA {

    }

    protected class InnerTargetB {

    }
}
