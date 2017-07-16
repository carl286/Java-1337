package com.l1337.l689;


import java.util.HashMap;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/130666/Java-DP-O(n)-solution.-Explanation-inline.
//    https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108239/Java-DP-Generic-Solution-for-M-subarrays
//    https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108238/Python-o(n)-time-o(1)-space.-Greedy-solution.
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        int preSum = 0;
        for (int i = 0; i < k - 1; ++i)
            preSum += nums[i];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = k - 1; i < nums.length; ++i) {
            preSum += nums[i];
            map.put(i - k + 1, preSum);
            preSum -= nums[i-k+1];
        }

        int [] dp1 = new int [nums.length];
        dp1[k-1] = 0;
        for (int i = k; i < nums.length; ++i) {
            //fill dp1[i], it covers nums 0 till nums i
            dp1[i] = dp1[i-1];
            if (map.get(i-k+1) > map.get(dp1[i])) {
                dp1[i] = i-k+1;
            }
        }
        
        int [][] dp2 = new int [nums.length][2];
        dp2[2*k-1][0] = 0;
        dp2[2*k-1][1] = k;
        for (int i = 2*k; i < nums.length; ++i) {
            dp2[i][0] = dp2[i-1][0];
            dp2[i][1] = dp2[i-1][1];
            if (map.get(i-k+1) + map.get(dp1[i-k])> map.get(dp2[i][0]) + map.get(dp2[i][1])) {
                dp2[i][0] = dp1[i-k];
                dp2[i][1] = i-k+1;
            }
        }

        int [][] dp3 = new int [nums.length][3];
        dp3[3*k-1][0] = 0;
        dp3[3*k-1][1] = k;
        dp3[3*k-1][2] = 2*k;
        for (int i = 3*k; i < nums.length; ++i) {
            dp3[i][0] = dp3[i-1][0];
            dp3[i][1] = dp3[i-1][1];
            dp3[i][2] = dp3[i-1][2];

//            System.out.println(map.get(i-k+1));
//            System.out.println(map.get(dp2[i-k][0]));
//            System.out.println(map.get(dp2[i-k][1]));
//
//            System.out.println(dp3[i][0]);
//            System.out.println(dp3[i][1]);
//            System.out.println(dp3[i][2]);

            if (map.get(i-k+1) + map.get(dp2[i-k][0]) + map.get(dp2[i-k][1])>
                    map.get(dp3[i][0]) + map.get(dp3[i][1]) + map.get(dp3[i][2])) {
                dp3[i][0] = dp2[i-k][0];
                dp3[i][1] = dp2[i-k][1];
                dp3[i][2] = i-k+1;
            }
        }

        return dp3[dp3.length-1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int ret [] = s.maxSumOfThreeSubarrays(new int[]{1,2,1,2,6,7,5,1}, 2);
//        int ret [] = s.maxSumOfThreeSubarrays(new int[]{1,2,1,2,1,2,3}, 2);
        for (int i = 0; i < ret.length; ++i)
            System.out.print(ret[i] + "\t");
        System.out.println();
    }
}
