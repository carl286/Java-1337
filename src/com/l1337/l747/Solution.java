package com.l1337.l747;


public class Solution {

    public int dominantIndex(int[] nums) {
        if (nums.length < 1)
            return -1;

        if (nums.length == 1)
            return 0;

        int secondLarget, largest;
        if (nums[0] < nums[1]) {
            secondLarget = 0;
            largest = 1;
        } else {
            secondLarget = 1;
            largest = 0;
        }

//        int secondLarget = Integer.MIN_VALUE;
//        int larget = Integer.MIN_VALUE;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > nums[largest]) {
                secondLarget = largest;
                largest = i;
            } else if (nums[i] > nums[secondLarget]) {
                secondLarget = i;
            }
        }

        if (nums[largest] >= (nums[secondLarget] << 1))
            return largest;
        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.dominantIndex(new int [] {0,0,1,2}));
    }
}
