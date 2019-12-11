package com.david.leetcode;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/majority-element
 */
public class Question169 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1};
//        int[] nums = new int[]{1, 1, 1, 1, 3, 2, 3};
//        System.out.println(new Solution().majorityElement(nums));
        System.out.println(new Solution1().majorityElement(nums));

    }

    //排序法 因为必有多数元素 排序后取中间下标就可
    static class Solution {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    //分而治之
    static class Solution1 {
        private int countInRange(int[] nums, int num, int lo, int hi) {
            int count = 0;
            for (int i = lo; i <= hi; i++) {
                if (nums[i] == num) {
                    count++;
                }
            }
            return count;
        }

        private int exec(int[] nums, int lo, int hi) {
            // base case; the only element in an array of size 1 is the majority
            // element.
            if (lo == hi) {
                return nums[lo];
            }

            // recurse on left and right halves of this slice.
            int mid = (hi - lo) / 2 + lo;
            int left = exec(nums, lo, mid);
            int right = exec(nums, mid + 1, hi);

            // if the two halves agree on the majority element, return it.
            if (left == right) {
                return left;
            }

            // otherwise, count each element and return the "winner".
            int leftCount = countInRange(nums, left, lo, hi);
            int rightCount = countInRange(nums, right, lo, hi);

            return leftCount > rightCount ? left : right;
        }

        public int majorityElement(int[] nums) {
            return exec(nums, 0, nums.length - 1);
        }
    }

}
