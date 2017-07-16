package com.l1337.l494;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C++-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
//    https://leetcode.com/problems/target-sum/solution/
    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length <= 0)
            return 0;

        //at least 1 elements.
        int max_sum = 0;
        for (int i : nums)
            max_sum += i;

        if ((max_sum < S && S > 0) || (S < 0 && max_sum + S < 0))
            return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 1);
        if (nums[0] != 0)
            map.put(-nums[0], 1);
        else
            map.put(-nums[0], 2);

        for (int i = 1; i < nums.length; ++i) {
            Map<Integer, Integer> next = new HashMap<>();

            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                next.put(key + nums[i], val + next.getOrDefault(key + nums[i], 0));
                next.put(key - nums[i], val + next.getOrDefault(key - nums[i], 0));
            }

            map = next;
        }

        return map.getOrDefault(S, 0);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
