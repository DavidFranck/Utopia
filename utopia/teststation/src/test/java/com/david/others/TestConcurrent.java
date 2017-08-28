import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestConcurrent {
    static volatile int j = 0;

    public static AtomicInteger integer = new AtomicInteger();
    @Test
    public void testAtomicInteger(){
        integer.getAndIncrement();
        System.out.println(integer);
    }
    @Test
    public void testLock(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        j++;
        lock.unlock();
        System.out.println(j);
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> j++).start();
        Thread.sleep(2000);
        j++;
        System.out.println(j);
    }

}
