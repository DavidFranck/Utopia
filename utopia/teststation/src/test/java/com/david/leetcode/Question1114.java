package com.david.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class Question1114 {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        new Thread(() -> {
            try {
                foo.second(() -> System.out.println("b"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.first(() -> System.out.println("a"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.third(() -> System.out.println("c"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static class Foo {
        private AtomicInteger count = new AtomicInteger(1);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            count.incrementAndGet();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (count.get() != 2) ;
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            count.incrementAndGet();
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (count.get() != 3) ;
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            count.incrementAndGet();
        }
    }
}
