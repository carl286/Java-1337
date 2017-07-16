package com.l1337.l697;


import java.util.HashMap;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/degree-of-an-array/discuss/108650/Easy-understand-Java-Solution-(Beats-100-solutions)
    public int findShortestSubArray(int[] nums) {
        int length = nums.length;

        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();

        int maxCount = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (left.containsKey(nums[i])) {
                right.put(nums[i], i);
                count.put(nums[i], count.get(nums[i]) + 1);
            } else {
                left.put(nums[i], i);
                right.put(nums[i], i);
                count.put(nums[i], 1);
            }
            maxCount = Math.max(maxCount, count.get(nums[i]));
        }

        int ret = nums.length;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == maxCount) {
                ret = Math.min(ret, right.get(entry.getKey()) - left.get(entry.getKey()) + 1);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findShortestSubArray(new int [] {1,2,2,3,1,4,2}));
    }
}
