package com.l1337.l41;


public class Solution {

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length <= 0)
            return 1;

        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] != i + 1 && nums[i] > 0  && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1)
                return i + 1;
        }
       return nums.length + 1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {2,2,3,2};
        System.out.println(s.firstMissingPositive(nums));
        System.out.println("Hello World");
    }
}
