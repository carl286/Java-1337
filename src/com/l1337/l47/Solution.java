package com.l1337.l47;


import java.util.*;

public class Solution {
    private void swap(Integer [] nums, int i, int j) {
        if (i != j) {
            Integer tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    private void helper(List<List<Integer>> ret, Integer [] nums, int index) {
        if (index == nums.length) {
            ret.add(Arrays.asList(Arrays.copyOf(nums, nums.length)));
            return;
        }

        int i = index;
        Set<Integer> hash = new HashSet<>();

        while (i < nums.length) {
            // think about what causes the duplicates
            if (!hash.contains(nums[i])) {
                swap(nums, index, i);
                helper(ret, nums, index + 1);
                swap(nums, index, i);
                hash.add(nums[i]);
            }
            ++i;
        }
    }

    // https://leetcode.com/submissions/detail/63582466/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Integer [] tmp = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++)
            tmp[i] = Integer.valueOf(nums[i]);
        // Arrays.sort(tmp);

        helper(ret, tmp, 0);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] hums = {1,2};
        List<List<Integer>> ret = s.permute(hums);

    }
}
