package com.l1337.l16;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int ClosestDelta = Integer.MAX_VALUE;
        int tmp = Integer.MIN_VALUE;
        while (i < nums.length)
        {
            int j = i + 1, k = nums.length - 1;

            while (j < k)
            {
                int sum = nums[i] + nums[j] + nums[k];
                int delta = sum - target;
                int newDelta = Math.abs(delta);
                if (newDelta < ClosestDelta) {
                    tmp = sum;
                    ClosestDelta = newDelta;
                }
                if ( delta == 0) {
                     break;
                }
                else if (delta > 0) {
                    do {
                        --k;
                    }
                    while(j < k && nums[k] == nums[k+1]);
                } if (delta < 0) {
                    do {
                        ++j;
                    }
                    while (j < k && nums[j] == nums[j-1]);
                }
            }

            do {
                ++i;
            }
            while (i < nums.length && nums[i] == nums[i-1]);
        }

        return tmp;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int[] nums = new int [] {2,4,8,16,32,64,128};
        int target = 82;
        System.out.println(s.threeSumClosest(nums, target));
    }
}
