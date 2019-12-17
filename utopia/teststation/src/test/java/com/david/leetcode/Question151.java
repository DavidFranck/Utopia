package com.david.leetcode;


public class Question151 {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("the   sky is  blue"));
    }

    static class Solution {
        public String reverseWords(String s) {
            StringBuilder res = new StringBuilder();
            s = s.trim(); // delete leading or trailing spaces.
            int left = s.length() - 1, right = s.length();
            while (left > 0) {
                //when meet the space append substring to sb
                if (s.charAt(left) == ' ') {
                    res.append(s.substring(left + 1, right));
                    res.append(' ');
                    while (s.charAt(left) == ' ') left--; // ignore extra spaces between words.
                    right = left + 1;
                }
                left--;
            }
            //append the last word
            return res.append(s.substring(0, right)).toString();
        }
    }
}
