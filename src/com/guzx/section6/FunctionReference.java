package com.guzx.section6;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 16:58
 * @describe
 */
public class FunctionReference {
    @FunctionalInterface
    interface UserFactory<U extends User> {
        U createdUser(int age, String name);
    }

    // 系统会根据createdUser的函数签名自动选择合适的构造函数
    // 后期对createdUser的调用都会委托给User的实际构造函数进行，从而创建User对象
    static UserFactory<User> userFactory = User::new;

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(1, "name" + i));
        }

        users.stream().map(User::getName).forEach(System.out::println);

        List<Double> list = new ArrayList<Double>();
        for (int i = 0; i < 10; i++) {
            list.add(Double.valueOf(i));
        }
        // 有同名的实例函数与静态函数，这样调用就有问题
//        list.stream().map(Double::toString).forEach(System.out::println);

    }


}
