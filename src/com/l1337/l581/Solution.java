package com.l1337.l581;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/shortest-unsorted-continuous-subarray/discuss/103066/Ideas-behind-the-O(n)-two-pass-and-one-pass-solutions
    public int findUnsortedSubarray(int[] nums) {
//        if (nums.length <= 1)
//            return 0;
//
//        int [] nums2 = Arrays.copyOf(nums, nums.length);
//        Arrays.sort(nums2);
//        int i = 0, j = nums.length - 1;
//        while (i < j && nums[i] == nums2[i]) {
//            ++i;
//        }
//
//        while (i < j && nums[j] == nums2[j]) {
//            --j;
//        }
//
//        return i == j ? 0 : j - i + 1;
        int i = 0, j = -1;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int l = 0, r = nums.length - 1; r >= 0; l++, r--) {
            max = Math.max(max, nums[l]);
            if (nums[l] != max) j = l;

            min = Math.min(min, nums[r]);
            if (nums[r] != min) i = r;
        }

        return (j - i + 1);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findUnsortedSubarray(new int [] {1,8,9,7}));
    }
}
