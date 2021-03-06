package com.guzx.section6;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 16:32
 * @describe
 */
public class User {
    private String name;
    private int age;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "age:" + age + " name:" + name;
    }
}
