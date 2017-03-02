package com.david.local;

import io.vertx.core.buffer.Buffer;
import org.junit.Test;

/**
 * Created by David
 * on 2017/1/4
 */
public class TestBuffer {
    @Test
    public void test1() {
        Buffer buffer = Buffer.buffer();
    }

    @Test
    public void test2() {
        Buffer buffer = Buffer.buffer("initial data", "UTF-8");
        System.out.println(buffer);
    }

    @Test
    public void test3() {
        Buffer buffer = Buffer.buffer();
        System.out.println("length:" + buffer.length());
        buffer.setByte(0, (byte) 127);
        buffer.setShort(2, (short) 127);
        buffer.setInt(4, 127);
        buffer.setLong(8, 127);
        buffer.setFloat(16, 127.0F);
        buffer.setDouble(20, 127.0D);
        System.out.println("length:" + buffer.length());
    }

    @Test
    public void test4() {
        Buffer buffer = Buffer.buffer();

        System.out.println("buffer.length() = " + buffer.length());

        buffer.appendByte((byte) 127);
        buffer.appendShort((short) 127);
        buffer.appendInt(127);
        buffer.appendLong(127);
        buffer.appendFloat(127.0F);
        buffer.appendDouble(127.0D);

        System.out.println("buffer.length() = " + buffer.length());
        byte aByte = buffer.getByte(0);
        short aShort = buffer.getShort(2);
        int anInt = buffer.getInt(4);
        long aLong = buffer.getLong(8);
        float aFloat = buffer.getFloat(16);
        double aDouble = buffer.getDouble(20);
        System.out.println(aByte);
        System.out.println(aShort);
        System.out.println(anInt);
        System.out.println(aLong);
        System.out.println(aFloat);
        System.out.println(aDouble);
    }

}
