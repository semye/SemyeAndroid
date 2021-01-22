package com.semye.base.generics;

/**
 * 工具人一号
 */
public class Token<T> {

    private T generics;

    public Token() {
    }

    public Token(T generics) {
        this.generics = generics;
    }

    public T getGenerics() {
        return generics;
    }

    public void setGenerics(T generics) {
        this.generics = generics;
    }
}
