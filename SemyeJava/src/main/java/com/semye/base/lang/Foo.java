package com.semye.base.lang;

public class Foo implements Comparable<Foo> {

    private String name;

    public String address;

    public Foo() {
    }

    public String getName() {
        return "yesheng";
    }

    public String function() {
        return name;
    }

    private String function1() {
        return address;
    }

    @Override
    public int compareTo(Foo o) {
        return this.name.length() - o.name.length();
    }
}
