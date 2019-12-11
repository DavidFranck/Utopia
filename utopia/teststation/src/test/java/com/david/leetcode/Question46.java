package com.david.leetcode;

import java.util.*;

/**
 * 全排序
 */
public class Question46 {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3};

//        System.out.println(Solution1.permute(ints));
        System.out.println(Solution2.permute(ints));
    }

    //插入法
    public static class Solution1 {
        public static List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> lists = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            rec(nums, lists, list, 1, nums.length);
            return lists;
        }

        /**
         * @param nums      需要排序的数组
         * @param res       结果数组
         * @param allowance 中间值寄存数组
         * @param start     数组开始下标
         * @param n         数组结束下标
         */
        public static void rec(int[] nums, List<List<Integer>> res, List<Integer> allowance, int start, int n) {
            if (start > n) {
                res.add(new ArrayList<>(allowance));
                return;
            }
            //循环，在每个index处插入新的元素
            for (int i = 0; i < start; i++) {
                allowance.add(i, nums[start - 1]);
                //深入下一层
                rec(nums, res, allowance, start + 1, n);
                //从下一层出来 临时值数组减少一层
                allowance.remove(i);
            }
        }
    }

    //回溯法
    public static class Solution2 {
        public static void backtrack(int n, ArrayList<Integer> nums, List<List<Integer>> output, int first) {
            // if all integers are used up
            if (first == n)
                output.add(new ArrayList<>(nums));
            for (int i = first; i < n; i++) {
                // place i-th integer first
                // in the current permutation
                Collections.swap(nums, first, i); // 排列 就是两两交换！！！
                // use next integers to complete the permutations
                backtrack(n, nums, output, first + 1);
                // backtrack
                Collections.swap(nums, first, i);
            }
        }

        public static List<List<Integer>> permute(int[] nums) {
            // init output list
            List<List<Integer>> output = new LinkedList<>();

            // convert nums into list since the output is a list of lists
            ArrayList<Integer> nums_lst = new ArrayList<Integer>();
            for (int num : nums)
                nums_lst.add(num);

            int n = nums.length;
            backtrack(n, nums_lst, output, 0);
            return output;
        }
    }

}
