package com.david.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question148 {
    public static void main(String[] args) {
//        int[] nums = {4, 2, 10, 6};
        int[] nums = {5, 2, 3, 1};
        List<Integer> integers = new Solution1().sortArray(nums);
//        List<Integer> integers = new Solution().sortArray(nums);
        System.out.println(integers);
//        Arrays.stream(ints).forEach(System.out::println);
    }

    //快速排序
    static class Solution1 {
        public List<Integer> sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            quickSort(nums, 0, nums.length - 1);
            return Arrays.stream(nums).boxed().collect(Collectors.toList());
        }

        public void quickSort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }
            int base = nums[(start + end) / 2];
            int left = start, right = end;
            while (left <= right) {
                while (left <= right && nums[left] < base) {
                    left++;
                }
                while (left <= right && nums[right] > base) {
                    right--;
                }
                if (left <= right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    left++;
                    right--;
                }
            }
            quickSort(nums, start, right);
            quickSort(nums, left, end);
        }
    }

    //归并排序
    public static class Solution {
        public List<Integer> sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            mergeSort(nums, 0, nums.length - 1);
            return Arrays.stream(nums).boxed().collect(Collectors.toList());
        }

        public void mergeSort(int[] nums, int start, int end) {
            //递归结束条件遍历结束
            if (start >= end) {
                return;
            }
            int mid = (start + end) / 2;//中间切分点
            mergeSort(nums, start, mid);//排列左边
            mergeSort(nums, mid + 1, end);//排列右面
            merge(nums, start, end, mid);//合并
        }

        public void merge(int[] nums, int start, int end, int mid) {
            int[] temp = new int[nums.length];
            int left = start, right = mid + 1;
            int index = start;
            //取小的放在左边
            while (left <= mid && right <= end) {
                if (nums[left] <= nums[right]) {
                    temp[index++] = nums[left++];
                } else {
                    temp[index++] = nums[right++];
                }
            }
            //把左边剩余的加到临时数组
            while (left <= mid) {
                temp[index++] = nums[left++];
            }
            //把右边剩余的加到临时数组
            while (right <= end) {
                temp[index++] = nums[right++];
            }
            //复制到原数组当前区间的位置上
            for (index = start; index <= end; index++) {
                nums[index] = temp[index];
            }
        }
    }

}
