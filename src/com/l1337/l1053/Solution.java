package com.l1337.l1053;


public class Solution {

    private void swap(int arr[] ,int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public int[] prevPermOpt1(int[] arr) {
        if (arr.length <= 1)
            return arr;

        int i = arr.length - 2;
        while (i >= 0 && arr[i] <= arr[i+1])
            --i;

        if (i < 0)
            return arr;

        int j = i + 1;
        while (j < arr.length && arr[j] < arr[i])
        {
            ++j;
        }

        if (arr.length - 1 != i + 1 && (arr[i+1] == arr[j-1]))
            swap(arr, i, i+1);
        else
            swap(arr, i, j - 1);

        return arr;
    }
//    https://leetcode.com/problems/previous-permutation-with-one-swap/discuss/299244/Similar-to-find-previous-permutation-written-in-Java
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] tmp = new int [] {1,9,5,7,9};
        for (int i : s.prevPermOpt1(tmp))
        {
            System.out.print(i + "\t");
        }
        System.out.println();

        tmp = new int [] {9,2,3,4,9};
        for (int i : s.prevPermOpt1(tmp))
        {
            System.out.print(i + "\t");
        }
        System.out.println();

        tmp = new int [] {3,2,1};
        for (int i : s.prevPermOpt1(tmp))
        {
            System.out.print(i + "\t");
        }
        System.out.println();

        tmp = new int [] {1,9,4,6,7};
        for (int i : s.prevPermOpt1(tmp))
        {
            System.out.print(i + "\t");
        }
        System.out.println();

        tmp = new int [] {3,1,1};
        for (int i : s.prevPermOpt1(tmp))
        {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
