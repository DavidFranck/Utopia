package com.david.algorathm;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 排序
 */
public class Sort {

    int[] array = new int[]{212, 3234, 23, 1231, 324, 36, 34, 1, 24, 53};

    @Before
    public void before() {
        System.out.println("\n============原始值====================");
        Arrays.stream(array).forEach(i -> System.out.print(i + " "));
    }

    @Test
    public void testQuickSort() {
        System.out.println("\n============快速排序====================");
        Arrays.stream(quickSort(array)).forEach(i -> System.out.print(i + " "));
    }

    @Test
    public void testQuickSort2() {
        quickSort2(array);
        System.out.println("\n============快速排序2====================");
        Arrays.stream(quickSort(array)).forEach(i -> System.out.print(i + " "));
    }

    public static int[] quickSort2(int[] a) {
        qSort(a, 0, a.length - 1);
        return a;
    }

    public static void qSort(int[] arr, int low, int high) {
        if (low >= high) { //递归退出
            return;
        }
        int left = low, right = high;//存下来低位 高位
        int pivot = arr[low];//取左面第一个arr[low]为基准
        while (low < high) {
            while (arr[low] < pivot) {//从左到右扫描 找到第一个大于基准的
                low++;
            }
            while (arr[high] > pivot) {//从右到左扫描 找到第一个小于基准的
                high--;
            }
            swap(arr, low, high);//置换 然后继续循环
        }
        swap(arr, low, left);//基准值回到正确位置
        qSort(arr, left, low - 1);//排序左面
        qSort(arr, low + 1, right);//排序右面
    }

    //================================================================

    /**
     * 快速排序
     * 1，定义i=0，j=A.lenght-1，i为第一个数的下标，j为最后一个数下标
     * 2，从数组的最后一个数Aj从右往左找，找到第一小于key的数，记为Aj；
     * 3，从数组的第一个数Ai 从左往右找，找到第一个大于key的数，记为Ai；
     * 4，交换Ai 和Aj 
     * 5，重复这个过程，直到 i=j
     * 6，调整key的位置，把A[i] 和key交换
     * 7, 分成两部分 递归调用
     */
    public static int[] quickSort(int[] a) {
        if (a.length > 0) {
            quickSortRecursion(a, 0, a.length - 1);
        }
        return a;
    }

    public static void quickSortRecursion(int[] data, int low, int high) {
        if (low < high) {
            int middle = partition(data, low, high);
            quickSortRecursion(data, low, middle - 1);
            quickSortRecursion(data, middle + 1, high);
        }
    }

    //以左后一个数字作为标准 排成    n<pivot... ,pivot, n>pivot...
    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        //比pivot小的都放在左面 i 存左面的下标
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, j, i);
            }
        }
        swap(arr, end, i + 1);
        return i + 1;
    }

    //交换数组两个元素
    private static void swap(int[] arr, int i1, int i2) {
        int swapTemp = arr[i2];
        arr[i2] = arr[i1];
        arr[i1] = swapTemp;
    }
}
