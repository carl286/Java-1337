package com.l1337.l413;


public class Solution {

//    https://leetcode.com/problems/arithmetic-slices/description/
    public int numberOfArithmeticSlices(int[] A) {

        int sum = 0;
        int count = 2;
        for (int i = 0; i < A.length - 2; i++) {
            if (isArithmeticSeq(A, i)) {
                ++count;
            } else {
                //do counting
                sum += f(count);
                count = 2;
            }
        }
        sum += f(count);
        return sum;
    }

    public int numberOfArithmeticSlicesDP(int[] a) {
        if (a.length < 3)
            return 0;
        int sum = 0;
        int dk = 0;
        int pre = a[1] - a[0];
        for (int i = 2; i < a.length; ++i) {
            int cur = a[i] - a[i - 1];
            if (cur != pre) {
                dk = 0;
            } else {
                ++dk;
                sum += dk;
            }
            pre = cur;
        }

        return sum;
    }

    //count how many splices of given N numbers
    private int f(int n) {
        if (n <= 2)
            return 0;

        return (int)((long) (n - 1) * (n - 2) / 2);
    }

    //assume i, i + 1, i + 2 are valid index
    private boolean isArithmeticSeq(int [] A, int i) {
        return ((long)A[i] + A[i + 2]) == A[i+1] * (long)2;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World X");
        int A [] = {1,2,3,4,5};
        System.out.println(s.numberOfArithmeticSlices(A));
//        must be at least of length 3 of the slice
//   it's acutally counting consective sequences
//
//
//
//
    }
}
