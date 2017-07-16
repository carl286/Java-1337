package com.l1337.l416;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/partition-equal-subset-sum/description/
    public boolean canPartition(int[] nums) {
        if (nums.length < 2)
            return false;

        //total should be even
        Set<Integer> set = new HashSet<>();
        int total = 0;

        for (int i : nums) {
            total += i;
            Set<Integer> set2 = new HashSet<>(set);
            set2.add(i);
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
                set2.add(i + it.next());
            }
            set = set2;
        }
        if ((total & 0x01) != 0)
            return false;
        else
            return set.contains(total / 2);
    }

    //Please improve it
//    https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
    public boolean canPartitionDP(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (((sum & 0x01) != 0) || sum == 0)
            return false;

        boolean dp [] = new boolean[1 + sum];
        for (int i: nums) {
            for (int k = dp.length - 1; k > 0; --k) {
                if (dp[k]) {
                    dp[k + i] = true;
                }
            }
            dp[i] = true;
        }

        return dp[sum / 2];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int a[] = {1, 2, 11, 5};
        System.out.println(s.canPartitionDP(a));
    }
}
