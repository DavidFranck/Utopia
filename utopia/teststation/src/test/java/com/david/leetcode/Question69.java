package com.david.leetcode;

/**
 * 取平方根
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class Question69 {
    public static void main(String[] args) {
//        System.out.println(new Solution2().mySqrt(4));
//        System.out.println(new Solution2().mySqrt(8));
//        System.out.println(new Solution2().mySqrt(9));

        System.out.println(new Solution1().mySqrt(4));
        System.out.println(new Solution1().mySqrt(8));
        System.out.println(new Solution1().mySqrt(9));
    }

    //二分法
    public static class Solution1 {
        public int mySqrt(int x) {
            if (x == 0) return 0;
            //left从0开始 right 从 (a/2)+1开始
            long left = 0;
            long right = (x >> 1) + 1;
            while (left < right) {
                //取右中位数
                long mid = (left + right + 1) >>> 1;
                if (mid * mid > x) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return (int) left;
        }

//        public int mySqrt(int x) {
//            long left = 0;
//            long right = x / 2 + 1;
//            while (left < right) {
//                long mid = (left + right + 1) >>> 1;
//                long square = mid * mid;
//                if (square > x) {
//                    right = mid - 1;
//                } else {
//                    left = mid;
//                }
//            }
//            return (int) left;
//        }
    }

    //    牛顿迭代
    public static class Solution2 {
        public int mySqrt(int a) {
            long x = a;
            while (x * x > a) {
                x = (x + a / x) / 2;
            }
            return (int) x;
        }
    }

}
