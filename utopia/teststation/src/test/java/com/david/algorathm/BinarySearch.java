package com.david.algorathm;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * 二分法 数组从小到大
 */
public class BinarySearch {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22222);
        int i = runBinarySearchIteratively(list.toArray(new Integer[]{0}), 11);
        int i2 = runBinarySearchRecursively(list.toArray(new Integer[]{0}), 12, 0, list.size());
        System.out.println(i);
        System.out.println(i2);
    }

    //循环
    public static int runBinarySearchIteratively(Integer[] array, int key) {
        int high = array.length;
        int low = 0;
        while (low <= high) {
            int mid = (high + low) / 2;//中位数
            if (array[mid] < key) {
                low = mid + 1;
            } else if (array[mid] > key) {
                high = mid - 1;
            } else if (array[mid] == key) {
                return mid;
            }
        }
        return -1;
    }

    //递归
    public static int runBinarySearchRecursively(Integer[] array, int key, int low, int high) {
        int mid = (low + high) / 2;
        if (high < low) return -1;//
        if (array[mid] == key) {
            return mid;
        } else if (array[mid] < key) {
            return runBinarySearchRecursively(array, key, mid + 1, high);
        } else {
            return runBinarySearchRecursively(array, key, low, mid - 1);
        }
    }
}
