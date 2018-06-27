package com.david.pattern.singleton;

/**
 * 饿汉式
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(getInstance() == getInstance());
        System.out.println(System.currentTimeMillis()-l);
    }
}
