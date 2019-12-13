package com.david.leetcode;


public class Question240 {
    public static void main(String[] args) {
//        int[][] matrix = {
//                {1, 4, 7, 11, 15},
//                {2, 5, 8, 12, 19},
//                {3, 6, 9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}
//        };
        int[][] matrix = {
                {1, 1}
        };
        boolean b = new Solution().searchMatrix(matrix, 8);
        System.out.println(b);
    }

    //从左下角开始走因为这样保证大或者小只向一个方向走
    public static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            //空的边界判定
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int row = matrix.length - 1;
            int col = 0;
            while (col < matrix[0].length && row >= 0) {
                if (matrix[row][col] > target) {
                    row--;
                } else if (matrix[row][col] < target) {
                    col++;
                } else if (matrix[row][col] == target) {
                    return true;
                }
            }
            return false;
        }
    }
}
