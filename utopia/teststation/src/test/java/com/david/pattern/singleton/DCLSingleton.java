package com.david.pattern.singleton;

/**
 * 双重锁校验 double-checked locking
 */
public class DCLSingleton {
    private volatile static DCLSingleton instance;

    private DCLSingleton() {
    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) instance = new DCLSingleton();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(getInstance() == getInstance());
        System.out.println(System.currentTimeMillis() - l);
    }
}
