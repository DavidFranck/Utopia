package com.david.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Question93 {
    public static void main(String[] args) {
        String ip = "25525511135";
        System.out.println(new Question93().new Solution1().restoreIpAddresses(ip));
    }

    //暴力法
    class Solution1 {
        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<>();
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    for (int c = 0; c < 4; c++) {
                        for (int d = 0; d < 4; d++) {
                            if (s.length() != (a + b + c + d)) continue;
                            String s1 = s.substring(0, a);
                            String s2 = s.substring(a, a + b);
                            String s3 = s.substring(a + b, a + b + c);
                            String s4 = s.substring(a + b + c, a + b + c + d);
                            if (helper(s1) && helper(s2) && helper(s3) && helper(s4)) {
                                res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                            }
                        }
                    }
                }
            }
            return res;
        }

        private boolean helper(String tmp) {
            return tmp != null && tmp.length() != 0 && tmp.length() <= 3 && (tmp.charAt(0) != '0' || tmp.length() <= 1) && Integer.parseInt(tmp) <= 255;
        }
    }
    //回溯法
}
