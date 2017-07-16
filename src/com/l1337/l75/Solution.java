package com.l1337.l75;

public class Solution {
    private final int RED = 0;
    private final int WHITE = 1;
    private final int BLUE = 2;

    private void swap(int [] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public void sortColors(int[] nums) {
        int cur = 0, l = -1, r = nums.length;
        while (cur < r) {
            switch (nums[cur]) {
                case RED:
                    swap(nums, cur++, ++l);
                    break;
                case WHITE:
                    ++cur;
                    break;
                case BLUE:
                    swap(nums, cur, --r);
                    break;
            }
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {2,0,2,1,1,0};
        s.sortColors(nums);
        for (int i = 0; i < nums.length; ++i)
            System.out.print(nums[i] + "\t");
        System.out.println();
    }
}
