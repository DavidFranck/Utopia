package com.david.others;

import org.junit.Test;

/**
 ①    a ^ a =0
 ②    a ^ b =b ^ a
 ③    a ^b ^ c = a ^ (b ^ c) = (a ^ b) ^ c;
 ④    d = a ^b ^ c 可以推出 a = d ^ b ^ c.
 ⑤    a ^ b ^a = b.
 */
public class TestBit {
    @Test
    public void main() {
        swap(1, 2);
        System.out.println(abs(1));
        System.out.println(abs(-1));
        System.out.println(isOdd(1));
        System.out.println(isOdd(2));
        System.out.println(1^1);
        System.out.println(1^2^3);
        System.out.println(0^2^3);
    }

    public void swap(int a, int b) {
        System.out.println(a + "-----" + b);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println(a + "-----" + b);
    }

    public int abs(int n) {
        return (n ^ (n >>> 31) - (n >> 31));
    }

    public boolean isOdd(int a) {
        return (a & 1) == 1;
    }

    int max(int a, int b) {
        return b & ((a - b) >> 31) | a & (~(a - b) >> 31);
    /*如果a>=b,(a-b)>>31为0，否则为-1*/
    }

    int min(int a, int b) {
        return a & ((a - b) >> 31) | b & (~(a - b) >> 31);
    /*如果a>=b,(a-b)>>31为0，否则为-1*/
    }

    boolean isSameSign(int x, int y) { //有0的情况例外
        return (x ^ y) >= 0; // true 表示 x和y有相同的符号， false表示x，y有相反的符号。
    }

    boolean isFactorialofTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    /*如果是2的幂，n一定是100... n-1就是1111....
       所以做与运算结果为0*/
    }
}
