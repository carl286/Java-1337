package com.l1337.l645;


import java.util.HashSet;
import java.util.Set;

public class Solution {
//    https://leetcode.com/problems/set-mismatch/discuss/105515/C++-6-lines-solution-with-explanation
//    https://leetcode.com/problems/set-mismatch/discuss/105513/XOR-one-pass
    public int[] findErrorNums(int[] nums) {
        int duplicate = 0;
        int sum = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (set.contains(nums[i]))
                duplicate = nums[i];
            else
                set.add(nums[i]);
        }
        int missing = (nums.length * (nums.length + 1) / 2) - sum + duplicate;
        return new int []{duplicate, missing};
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        for (int i : s.findErrorNums(new int []{1,2,2,4})) {
            System.out.print(i + "\t");
        }
    }
}
