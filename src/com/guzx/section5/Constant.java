package com.guzx.section5;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:07
 * @describe 不变模式
 */
public final class Constant {
    private final int id;
    private final String name;

    public Constant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
