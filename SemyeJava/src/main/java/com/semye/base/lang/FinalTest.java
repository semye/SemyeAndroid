package com.semye.base.lang;

/**
 * Created by yesheng on 15/12/4.
 */
public class FinalTest {

    public static class Foo {
        public final int number;
        public static final int A = 1;
        public final int B = 2;
        public static int C = 3;
        public int D = 4;

        public Foo() {
            number = 999;
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        System.out.println(Foo.A);
        System.out.println(foo.B);
        System.out.println(Foo.C);
        System.out.println(foo.D);
        System.out.println(foo.number);
        Foo.C = 7;
        foo.D = 10;
        System.out.println(Foo.C);
        System.out.println(foo.D);
//        Foo.A = 9;
//        foo.B = 8;
//        foo.number = 999;
    }

}
