package com.l1337.l15;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();

        Arrays.sort(nums);
        int i = 0;

        while (i < nums.length)
        {
            int j = i + 1, k = nums.length - 1;
            while (j < k)
            {
                int sum = nums[i] + nums[j] + nums[k];
                if ( sum == 0) {
                    ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // break;
                    do {
                        --k;
                    }
                    while(j < k && nums[k] == nums[k+1]);
                    do {
                        ++j;
                    }
                    while (j < k && nums[j] == nums[j-1]);
                }
                else if (sum > 0) {
                    do {
                        --k;
                    }
                    while(j < k && nums[k] == nums[k+1]);
                } if (sum < 0) {
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

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
