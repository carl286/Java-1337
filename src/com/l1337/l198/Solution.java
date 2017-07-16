package com.l1337.l198;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/house-robber/discuss/55761/Java-0ms-solution-using-Dynamic-Programming
//    https://leetcode.com/problems/house-robber/discuss/55695/JAVA-DP-Solution-O(n)-runtime-and-O(1)-space-with-inline-comment
//    https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
    public int rob(int[] nums) {
        if (nums.length <= 0)
            return 0;
        int [] taken = new int [nums.length];
        int [] notTaken = new int [nums.length];

        //init
        taken[0] = nums[0];
        for (int i = 1; i < nums.length; ++i)
        {
            taken[i] = nums[i] + notTaken[i-1];
            notTaken[i] = Math.max(notTaken[i-1], taken[i-1]);
        }

        return Math.max(taken[nums.length - 1], notTaken[nums.length - 1]);
    }

    public int robFeb25_21(int[] nums) {
        int n = nums.length;
        int [] taken = new int [n];
        int [] not_taken = new int [n];
        taken[0] = nums[0];

        for (int i = 1; i < nums.length; ++i)
        {
            taken[i] = nums[i] + not_taken[i-1];
            not_taken[i] = Math.max(taken[i-1], not_taken[i-1]);
        }

        return Math.max(taken[n-1], not_taken[n-1]);
    }



















    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.rob(new int [] {2,7,9,3,1}));
    }
}
