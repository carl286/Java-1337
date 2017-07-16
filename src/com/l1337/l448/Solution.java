package com.l1337.l448;


import java.util.ArrayList;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
    private void swap(int [] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1)
                ret.add(i + 1);
        }
        return ret;
    }

//    https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
//    https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92980/5-line-Java-Easy-understanding
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
