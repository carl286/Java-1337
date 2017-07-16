package com.l1337.l565;


public class Solution {

    public int arrayNesting(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; ++i) {
            int counter = 0, next = i;
            while (next >= 0 && nums[next] >= 0) {
                ++counter;
                next = nums[next];
                nums[i] = -1;
            }
            ret = Math.max(ret, counter);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.arrayNesting(new int [] {5,4,0,3,1,6,2}));
//        System.out.println(s.arrayNesting(new int [] {1,2,0}));
    }
}
