package com.l1337.l396;


public class Solution {
    public int maxRotateFunction(int[] A) {
        int allSum = 0;
        int len = A.length;
        int F = 0;
        for (int i = 0; i < len; i++) {
            F += i * A[i];
            allSum += A[i];
        }
        int max = F;
        for (int i = len - 1; i >= 1; i--) {
            F = F + allSum - len * A[i];
            max = Math.max(F, max);
        }
        return max;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int A [] = {4, 3, 2, 6};
        System.out.println(s.maxRotateFunction(A));
    }
}
