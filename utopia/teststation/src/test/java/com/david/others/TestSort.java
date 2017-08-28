package com.david.others;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class TestSort {
    @Test
    public void BubbleSort() {
        int[] source = new int[]{35, 765, 22, 56, 323, 66};
        for (int i = 0; i < source.length - 1; i++) {
            for (int j = 0; j < source.length - i - 1; j++) {
                if (source[j] < source[j + 1]) {
                    int temp = source[j + 1];
                    source[j + 1] = source[j];
                    source[j] = temp;
                }
                }
        }
        for (int i = 0; i < source.length; i++) {
            System.out.println(source[i]);
        }
    }

    @Test
    public void selectSort() {
        int[] source = new int[]{35, 765, 22, 56, 323, 66};
        for (int i = 0; i < source.length ; i++) {
            for (int j = i + 1; j < source.length ; j++) {
                if (source[i] > source[j]) {
                    int temp = source[j];
                    source[j] = source[i];
                    source[i] = temp;
                }
            }
        }
        for (int i = 0; i < source.length; i++) {
            System.out.println(source[i]);
        }
    }

    @Test
    public void sort() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 4, 3, 8, 2);
        Collections.sort(integers);
        integers.forEach(System.out::println);
    }
}
