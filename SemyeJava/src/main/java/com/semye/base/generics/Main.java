package com.semye.base.generics;

/**
 * 泛型
 * java中的泛型只在编译阶段有效
 * 如下
 * List<String> stringArrayList = new ArrayList<String>();
 * List<Integer> integerArrayList = new ArrayList<Integer>();
 * Class classStringArrayList = stringArrayList.getClass();
 * Class classIntegerArrayList = integerArrayList.getClass();
 * if(classStringArrayList.equals(classIntegerArrayList)){
 * Log.d("泛型测试","类型相同");
 * }
 * 输出结果：D/泛型测试: 类型相同
 * <p>
 * 泛型类
 * 泛型方法
 * 泛型接口
 * <p>
 * 通配符？
 * <p>
 * 泛型上下边界
 * <p>
 * 参考文章：https://blog.csdn.net/s10461/article/details/53941091
 * <p>
 * 协变和逆变
 * 参考文章：https://www.jianshu.com/p/71a5d70c180e
 * 协变常用于函数返回值
 * 逆变常用与函数参数
 * <p>
 * extends限定了通配符类型的上界，所以我们可以安全地从其中读取；
 * 而super限定了通配符类型的下界，所以我们可以安全地向其中写入。
 */
public class Main {

    public static void main(String[] args) {
        Token<Integer> token1 = new Token<Integer>(12324);
        Token<String> token2 = new Token<String>("12324");
        Token<Number> token3 = new Token<Number>();
        //不能对确切的泛型使用instanceof 会编译报错
        //token instanceof Token<Integer>
        print(token1);
        print(token2);
        print3(token3);
    }

    /**
     * 通配符的使用
     * 此处?是类型实参，而不是类型形参
     *
     * @param token token
     */
    private static void print(Token<?> token) {
        System.out.println(token.getGenerics());
    }

    /**
     * 为泛型添加上边界，即传入的类型实参必须是指定类型的子类型
     * 协变
     */
    private static Token<? extends Number> print2() {
        return new Token<Integer>(1);
    }

    /**
     * 为泛型添加上边界，即传入的类型实参必须是指定类型的子类型
     * 协变
     */
    private static Token<? extends Number> print3() {
        return new Token<Byte>();
    }

    /**
     * 为泛型添加下边界，即传入的类型实参必须是指定类型的父类型
     * 逆变
     *
     * @param token token
     */
    private static void print3(Token<? super Integer> token) {
        System.out.println(token.getGenerics());
    }
}
