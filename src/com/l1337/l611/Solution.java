package com.l1337.l611;


import java.util.Arrays;

public class Solution {

    int binarySearch(int[] nums, int fromIndex, int toIndex, int key) {
        //nums[x] >= key, nums[x-1] < key
        int l = fromIndex - 1, r = toIndex;
        while (l + 1 != r) {
            int mid = ((r - l) >> 1) + l;
            if (nums[mid] < key)
                l = mid;
            else
                r = mid;
        }

        return r;
    }

//    https://leetcode.com/problems/valid-triangle-number/discuss/104174/Java-O(n2)-Time-O(1)-Space
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ret = 0;

        for (int i = 0; i + 2< nums.length; ++i) {
            for (int j = i + 1; j + 1 < nums.length; ++j) {
                int k = binarySearch(nums, j + 1, nums.length, nums[i] + nums[j]);
                ret += (k - j - 1);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
