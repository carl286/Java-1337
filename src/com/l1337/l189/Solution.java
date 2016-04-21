package com.l1337.l189;


public class Solution {

//    http://www.programcreek.com/2015/03/rotate-array-in-java/
    //a>= 0, b >= 0
    int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k <= 0)
            return;
        int length = nums.length;
        k %= length;
        if (k == 0)
            return;

        int gcd = gcd(length, k);
        for (int i = 0; i < gcd; ++i) {
            int m = i, n = (m + k) % length;
            int tmp = nums[m];
            while (n != i) {
                int next = nums[n];
                nums[n] = tmp;
                tmp = next;
                m = n;
                n = (m + k) % length;
            }
            nums[n] = tmp;
        }

    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
