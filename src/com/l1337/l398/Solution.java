package com.l1337.l398;


import java.util.*;

public class Solution {
    private final Map<Integer, List<Integer>> mapper;
    private final Random rnd;
    public Solution(int[] nums) {
        rnd = new Random();
        mapper = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (!mapper.containsKey(nums[i]))
                mapper.put(nums[i], new ArrayList<>());
            mapper.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> l = mapper.get(target);
        return l.get((int)(Math.random() * l.size()));
    }

    public static void main(String [] args) {
        int nums [] = {1,2,3,3,3};
        int target = 3;
        Solution s = new Solution(nums);
        int param_1 = s.pick(target);
        System.out.println(param_1);
    }
}
