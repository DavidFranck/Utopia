package com.david.core;

import org.junit.Test;

public class StringTest {

    @Test
    public void test1() {
        String baseStr = "baseStr";
        final String baseFinalStr = "baseStr";

        String str1 = "baseStr01";
        String str2 = "baseStr" + "01";
        String str3 = baseStr + "01";
        String str4 = baseFinalStr + "01";
        String str5 = new String("baseStr01").intern();

        System.out.println(str1 == str2);//#3 true
        System.out.println(str1 == str3);//#4 false
        System.out.println(str1 == str4);//#5 true
        System.out.println(str1 == str5);//#6 true
    }

    @Test
    public void test2() {
        String str2 = new String("str") + new String("01");
        str2.intern();
        String str1 = "str01";
        System.out.println(str2 == str1);//#7
    }
}
