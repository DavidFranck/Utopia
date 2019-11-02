package com.david.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//reentrant lock condition
public class TestCondition {
    public static void main(String[] args) {
        final Task myTask = new Task();
        Thread t1 = new Thread(myTask::execWait);
        t1.setName("t1");
        Thread t2 = new Thread(myTask::execSignal);
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    public static class Task {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void execWait() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " await start");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " await end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void execSignal() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " signal start");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + " signal end");
            lock.unlock();
        }
    }
}
