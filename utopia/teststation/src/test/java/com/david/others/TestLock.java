package com.david.others;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class TestLock {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2);
//        atomicInteger.addAndGet(1);
        new Thread(() -> IntStream.range(2000, 3000).forEach(System.out::println)).start();
        new Thread(() -> IntStream.range(3000, 4000).forEach(System.out::println)).start();
        new Thread(() -> IntStream.range(4000, 5000).forEach(System.out::println)).start();

    }

    //可重入锁
    public static class SpinLock1 {
        private AtomicReference<Thread> owner = new AtomicReference<>();
        private int count = 0;

        public void lock() {
            Thread thread = Thread.currentThread();
            if (thread == owner.get()) {
                count++;
                return;
            }
            while (!owner.compareAndSet(null, thread)) {
            }
        }

        public void unlock() {
            Thread thread = Thread.currentThread();
            if (thread == owner.get()) {
                if (count != 0) {
                    count--;
                } else {
                    owner.compareAndSet(thread, null);
                }
            }

        }

    }

    //自旋锁(错误的)
    public static class SpinLock {
        private AtomicReference<Thread> owner = new AtomicReference<>();

        public void lock() {
            Thread thread = Thread.currentThread();
            while (!owner.compareAndSet(null, thread)) {
            }
        }

        public void unlock() {
            Thread thread = Thread.currentThread();
            owner.compareAndSet(thread, null);
        }
    }

    //synchronized
    public static class TestSync implements Runnable {

        public synchronized void get() {
            System.out.println(Thread.currentThread().getId());
            set();
        }

        public synchronized void set() {
            System.out.println(Thread.currentThread().getId());
        }

        @Override
        public void run() {
            get();
        }

        public static void main(String[] args) {
            TestSync test1 = new TestSync();
            new Thread(test1).start();
            new Thread(test1).start();
            new Thread(test1).start();
        }
    }

    //ReentrantLock
    public static class TestReentrantLock implements Runnable {
        ReentrantLock lock = new ReentrantLock();

        public void get() {
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            set();
            lock.unlock();
        }

        public void set() {
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            lock.unlock();
        }

        @Override
        public void run() {
            IntStream.range(0, 10000).forEach(value -> get());
        }

        public static void main(String[] args) {
            TestReentrantLock testReentrantLock = new TestReentrantLock();
            new Thread(testReentrantLock).start();
            new Thread(testReentrantLock).start();
            new Thread(testReentrantLock).start();
        }
    }
}
