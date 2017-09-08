package com.david.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class TestProductConsumer {

    public static void main(String[] args) {
        ProductQueue<Integer> queue = new ProductQueue<>();
        new Thread(() -> IntStream.range(0, 100).forEach(value -> {
            try {
                queue.put(value);
//                TimeUnit.SECONDS.sleep(1);
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();

        new Thread(() -> IntStream.range(0, 100).forEach(value -> {
            try {
                Integer take = queue.take();
//                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();

    }


    public static class ProductQueue<T> {
        private final T[] items;
        private final Lock lock = new ReentrantLock();
//        private Condition notFull = lock.newCondition();
//        private Condition notEmpty = lock.newCondition();
        private Condition condition = lock.newCondition();
        private int head, tail, count;

        public ProductQueue(int size) {
            this.items = (T[]) new Object[size];
        }

        public ProductQueue() {
            this(200);
        }

        public void put(T t) throws InterruptedException {
            lock.lock();
            try {
                while (count == getCapacity()) {
                    condition.await();
                }
                items[tail] = t;
                if (++tail == getCapacity()) {
                    tail = 0;
                }
                ++count;
                condition.signalAll();
                System.out.println("put:" + t + "  count:" + count + "  head:" + head + "  tail:" + tail);
            } finally {
                lock.unlock();
            }
        }

        public T take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0) {
                    condition.await();
                }
                T ret = items[head];
                items[head] = null;
                if (++head == getCapacity()) {
                    head = 0;
                }
                --count;
                condition.signalAll();
                System.out.println("take:" + ret + "  count:" + count + "  head:" + head + "  tail:" + tail);
                return ret;
            } finally {
                lock.unlock();
            }
        }


        public int getCapacity() {
            return items.length;
        }

        public int size() {
            lock.lock();
            try {
                return count;
            } finally {
                lock.unlock();
            }
        }

    }
}
