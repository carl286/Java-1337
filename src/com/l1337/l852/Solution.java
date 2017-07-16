package com.l1337.l852;


public class Solution {

    public int peakIndexInMountainArray(int[] arr) {
        int i = 1;
        while (i < arr.length && arr[i-1] < arr[i])
        {
            ++i;
        }

        return i-1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
