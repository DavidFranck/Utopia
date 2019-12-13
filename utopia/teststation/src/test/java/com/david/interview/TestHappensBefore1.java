package com.david.interview;

public class TestHappensBefore1 {
    static int a = 1;

    public static void main(String[] args){
        a=999;
        Thread tb = new Thread(() -> {
            System.out.println(a);
        });
        a = 2;
        tb.start();
    }

//    public static void main(String[] args){
//        Thread tb = new Thread(() -> {
//            a = 2;
//        });
//        Thread ta = new Thread(() -> {
//            try {
//                tb.join();
//            } catch (InterruptedException e) {
//                //NO
//            }
//            System.out.println(a);
//        });
//
//        ta.start();
//        tb.start();
//    }
}
