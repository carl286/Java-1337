package com.l1337.l46;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        for (int i = index; i < nums.length; ++i) {
            swap(nums, index, i);
            helper(ret, nums, index + 1);
            swap(nums, index, i);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Integer [] nums2 = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        helper(ret, nums2, 0);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] hums = {1,2};
        List<List<Integer>> ret = s.permute(hums);

    }
}
