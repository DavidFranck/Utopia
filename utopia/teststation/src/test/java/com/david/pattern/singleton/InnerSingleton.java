package com.david.pattern.singleton;

public class InnerSingleton {
    private static class SingletonHolder {
        private static final InnerSingleton instance = new InnerSingleton();
    }

    private InnerSingleton() {
    }

    public static InnerSingleton getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(getInstance() == getInstance());
        System.out.println(System.currentTimeMillis() - l);
    }
}
