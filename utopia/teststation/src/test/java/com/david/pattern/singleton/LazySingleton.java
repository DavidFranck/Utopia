package com.david.pattern.singleton;

/**
 * 线程安全懒汉 效率低
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) instance = new LazySingleton();
        return instance;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(getInstance() == getInstance());
        System.out.println(System.currentTimeMillis()-l);
    }
}
