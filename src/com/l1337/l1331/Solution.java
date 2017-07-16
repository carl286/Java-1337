package com.l1337.l1331;


import java.util.Arrays;

public class Solution {
//    https://leetcode.com/problems/rank-transform-of-an-array/discuss/489753/JavaC%2B%2BPython-HashMap
    public int[] arrayRankTransform(int[] arr) {
        if (arr.length <= 0)
            return arr;

        //arr.length >= 1
        int[][] copy = new int [arr.length][2];
        for(int i = 0; i < arr.length; ++i)
        {
            copy[i][0] = i;
            copy[i][1] = arr[i];
        }

        Arrays.sort(copy, (a,b) -> (a[1]-b[1]));
        int lastVal = arr[copy[0][0]];
        int lastRank = 1;
        arr[copy[0][0]] = lastRank;

        for(int i = 1; i < copy.length; ++i)
        {
            if (arr[copy[i][0]] != lastVal)
            {
                ++lastRank;
                lastVal = arr[copy[i][0]];
            }
            arr[copy[i][0]] = lastRank;
        }

        return arr;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
