package com.guzx.section6;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 15:08
 * @describe
 */
@FunctionalInterface
public interface FunctionInterface {
    /**
     * 函数式接口中的唯一抽象方法
     * @param x
     */
    void intHandle(int x);

    /**
     * 被object实现的方法不视为抽象方法
     * @param o
     * @return
     */
    @Override
    boolean equals(Object o);

    /**
     * 接口的默认方式（实例方法）
     * @param x
     */
    default void doubleHandle(double x) {
        System.out.println(x);
    }
}
