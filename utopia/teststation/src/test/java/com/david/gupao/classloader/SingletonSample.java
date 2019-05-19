package com.david.gupao.classloader;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * instance 在上面的时候结果为 1 0
 * instance 在下面的时候结果为 1 1
 * 因为instance在上面的时候count2 先++在赋值成0
 */
public class SingletonSample {
    public static SingletonSample instace = new SingletonSample();//1
    public static int count;
    public static int count1 = 0;//3

    public static SingletonSample getInstace() {
        return instace;
    }

    public SingletonSample() {//2
        count++;
        count1++;
    }

    public static void main(String[] args) {
        SingletonSample.getInstace();
        System.out.println(count);
        System.out.println(count1);
    }
}
