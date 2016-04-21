package com.l1337.l377;


import java.util.Arrays;

public class Solution {

    /*
    //TLE
    private int combinationSum4Helper(int[] nums, int [] table, int target) {
        if (table[target] != 0)
            return table[target];
        int total = 0;
        int i;
        for (i = 0; i < nums.length && nums[i] <= target; ++i) {
            total += combinationSum4Helper(nums, table, target - nums[i]);
        }
        table[target] = total;
        return total;
    }
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int [] table = new int [target+1];
        table[0] = 1;
        return combinationSum4Helper(nums, table, target);
    }
    */

//    http://www.programcreek.com/2014/07/leetcode-combination-sum-iv-java/
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int [] table = new int [target+1];
        for (int i = 0; i < nums.length; ++i)
            if (nums[i] <= target)
                table[nums[i]] = 1;
            else
                break;
        for (int i = 1; i < table.length; ++i) {
           if (table[i] != 0) {
               for (int k = 0; k < nums.length; ++k) {
                   if (i + nums[k] <= target)
                        table[i + nums[k]] += table[i];
                   else
                       break;
               }
           }
        }
        return table[target];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {1,2,3};
        int target = 4;
        System.out.println(s.combinationSum4(nums, target));
    }
}

//https://www.hrwhisper.me/leetcode-combination-sum-iv/