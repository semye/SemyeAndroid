package com.semye.base.pattern.behavioral.iterator;

/**
 * Created by semye on 2018/10/22.
 */
public class NameIterator implements Iterator {

    private String names[];

    private int index = 0;

    public NameIterator(String[] names) {
        this.names = names;
    }

    @Override
    public boolean hasNext() {
        return index < names.length;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return names[index++];
        }
        return null;
    }
}
