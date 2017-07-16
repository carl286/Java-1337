package com.l1337.l31;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private void swap(int nums[], int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    private void reverse(int [] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            ++start;
            --end;
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums.length <= 1)
            return;

        int last = nums.length - 1;
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) {
            --i;
        }
        reverse(nums, i + 1, nums.length - 1);
        if (i >= 0) {
            int j = i + 1;
            while (j < nums.length && nums[j] <= nums[i]) {
                ++j;
            }
            //nums[j] > nums[i]
            swap(nums,i,j);
        }

    }

    public static void main(String [] args) {
        Solution s = new Solution();

    }
}
