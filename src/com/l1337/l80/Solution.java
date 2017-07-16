package com.l1337.l80;

public class Solution {
    private int maxDuplicates = 2;

    public int removeDuplicates(int[] nums) {
        int ret = 0;
        int i = 0;
        while (i < nums.length) {
            int j = i;
            while (j < nums.length && nums[i] == nums[j]) {
                if (j - i + 1 <= maxDuplicates)
                    nums[ret++] = nums[j++];
                else
                    j++;
            }
            i = j;
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = new int[] {1,1,1,2,2,3};
        s.removeDuplicates(nums);
    }
}
