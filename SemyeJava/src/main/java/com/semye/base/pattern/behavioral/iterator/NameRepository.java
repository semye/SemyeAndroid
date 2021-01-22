package com.semye.base.pattern.behavioral.iterator;

/**
 * Created by yesheng on 2016/12/30.
 */
public class NameRepository implements Container {
    public String[] names = {"Robert", "John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator(names);
    }
}
