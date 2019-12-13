package com.david.interview;

/**
 * happens-before
 * 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在后面的操作；
 * 锁定规则：在监视器锁上的解锁操作必须在同一个监视器上的加锁操作之前执行。
 * volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作；
 * 传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出操作A先行发生于操作C；
 * 线程启动规则：Thread对象的start()方法先行发生于此线程的每一个动作；
 * 线程中断规则：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生；
 * 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行；
 * 对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法的开始；
 */
public class TestHappensBefore {
    static int num = 0;
    static volatile boolean flag = false;

    /**
     * 首先，红色和黄色表示不同的线程操作。
     * 红色线程对 num 变量做 ++，然后修改了 volatile 变量，这个是符合 程序次序规则的。也就是 1 HB 2.
     * 红色线程对 volatile 的写 HB 黄色线程对 volatile 的读，也就是 2 HB 3.
     * 黄色线程读取 volatile 变量，然后对 num 变量做 ++，符合 程序次序规则，也就是 3 HB 4.
     * 根据传递性规则，1 肯定 HB 4. 所以，1 的修改对 4来说都是可见的。
     */
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (; 100 > num; ) {
                //1. ++num
                if (!flag && (num == 0 || ++num % 2 == 0)) {
                    System.out.println(num);
                    //2写 volatile flag
                    flag = true;
                }
            }
        }
        );

        Thread t2 = new Thread(() -> {
            for (; 100 > num; ) {
                //3.读volatile flag
                //4.++n
                if (flag && (++num % 2 != 0)) {
                    System.out.println(num);
                    flag = false;
                }
            }
        }
        );

        t1.start();
        t2.start();
    }
}
