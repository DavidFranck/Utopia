package com.david.gupao.interview;

import java.lang.reflect.Field;

public class IntegerSwap {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Integer a = 1, b = 2, c = 1, d = 129, e = 129;
        //因为自动装箱 从缓存中拿所以是一个对象
        System.out.println(a == c);
        System.out.println(d == e);
        System.out.println(a == new Integer(1));


        System.out.println(a + "---" + b);
//        swap(a, b);
        System.out.println(a + "---" + b);

    }

    //反射会扰乱缓存 是错误的！！！
    public static void swap(Integer i1, Integer i2) throws NoSuchFieldException, IllegalAccessException {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        int tmp = i1.intValue();
        field.set(i1, i2.intValue());
        field.set(i2, tmp);
    }
}
