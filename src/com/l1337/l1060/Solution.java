package com.l1337.l1060;


import java.util.Arrays;

public class Solution {

    /*
    Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
Explanation:
The first missing number is 5.
Example 2:

Input: A = [4,7,9,10], K = 3
Output: 8
Explanation:
The missing numbers are [5,6,8,…], hence the third missing number is 8.
Example 3:

Input: A = [1,2,4], K = 3
Output: 6
Explanation:
The missing numbers are [3,5,6,7,…], hence the third missing number is 6.


Note:

1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8
     */
//    https://xingxingpark.com/Leetcode-1060-Missing-Element-in-Sorted-Array/
    public int missingElement(int[] nums, int k) {
        int l = -1, r = nums.length;

        while (l + 1 != r)
        {
            int mid = ((r - l) >> 1) + l;
            int count = nums[mid] - nums[0] - mid;
            if (count < k)
            {
                l = mid;

            }
            else
            {
                r = mid;
            }
        }

        //r is the expected;
        return nums[0] + k + l;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        for (int k = 1; k <=10; ++k)
        System.out.println(s.missingElement(new int [] {4,7,9,10}, k));
    }
}
