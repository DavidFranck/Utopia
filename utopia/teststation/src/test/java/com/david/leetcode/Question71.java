package com.david.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Question71 {
    public static void main(String[] args) {
//        String path = "/a/../../b/../c//.//";
        String path = "/home//foo/";
        System.out.println(new Question71().new Solution().simplifyPath(path));
    }

    class Solution {
        public String simplifyPath(String path) {
            StringBuilder sb = new StringBuilder();
            LinkedList<String> stack = new LinkedList<>();
            String[] split = path.split("/");
            for (String p : split) {
                if ("..".equals(p)) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (!p.isEmpty() && !".".equals(p)) {
                    stack.push(p);
                }
            }
            for (String p : stack) {
                sb.insert(0, p);
                sb.insert(0, "/");
            }
            if (sb.length() == 0) {
                sb.append("/");
            }
            return sb.toString();
        }
    }
}
