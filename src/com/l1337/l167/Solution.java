package com.l1337.l167;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51239/Share-my-java-AC-solution.
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i + 1 < numbers.length; ++i)
        {
            int j = Arrays.binarySearch(numbers, i + 1, numbers.length, target - numbers[i]);
            if (j >= 0)
                return new int [] {i + 1, j + 1};
        }

        return null;
    }
}
