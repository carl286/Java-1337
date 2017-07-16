package com.l1337.l506;


import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    private void swap(int [] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

//    https://leetcode.com/problems/relative-ranks/discuss/166227/Java-solution-using-TreeMap
//    https://leetcode.com/problems/relative-ranks/discuss/98492/Java-6ms-solution-O(n)-without-sorting
    public String[] findRelativeRanks(int[] nums) {
        String[] ret = new String [nums.length];
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2);
//        for (int i = 0, j = nums2.length - 1; i < j; ++i, --j) {
//            swap(nums2, i, j);
//        }

        for (int i = 0; i < nums.length; ++i) {
            int key = nums[i];
            int index = Arrays.binarySearch(nums2, key);
            if (index == nums.length - 1) {
                ret[i] = "Gold Medal";
            } else if (index == nums.length - 2) {
                ret[i] = "Silver Medal";
            } else if (index == nums.length - 3) {
                ret[i] = "Bronze Medal";
            } else {
                ret[i] = new Integer(nums.length - index).toString();
            }
        }


        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = new int [] {10,3,8,9,4};
        for (String ss : s.findRelativeRanks(nums)) {
            System.out.println(ss);
        }
    }
}
