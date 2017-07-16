package com.l1337.l136;


public class Solution {

    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i: nums
             ) {
            ret ^= i;
        }

        return ret;
    }

    // https://leetcode.com/submissions/detail/60944972/
    // https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
    public int singleNumberII(int[] nums) {
        //this is tricky
        return 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
