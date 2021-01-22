package com.semye.base.pattern.behavioral.iterator;

/**
 * Created by yesheng on 2016/12/30.
 */
public class Client {

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for (Iterator iterator = namesRepository.getIterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            System.out.println("Name : " + name);
        }
    }
}
