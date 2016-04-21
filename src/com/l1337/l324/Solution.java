package com.l1337.l324;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//324. Wiggle Sort II
//https://leetcode.com/problems/wiggle-sort-ii/
public class Solution {

    /*
    Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
    Example:
            (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
            (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
    Note:
    You may assume all input has valid answer.
    Follow Up:
    Can you do it in O(n) time and/or in-place with O(1) extra space?
    */
    void swap(int [] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    void reverse(int [] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

//    https://leetcode.com/submissions/detail/57459193/
    //mostly idea is similar, depends on how much order do you want...
//    https://leetcode.com/submissions/detail/57463643/
    public void wiggleSort(int[] nums) {
        //This is a tricky one...

        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        int s = (nums.length + 1) >> 1, t = nums.length;
        for (int i = 0; i < nums.length; i++) {
            temp[i] = (i & 1) == 0 ?  nums[--s] : nums[--t] ;
        }

        for (int i = 0; i < nums.length; i++)
            nums[i] = temp[i];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {1,1,1,2,2};
        s.wiggleSort(nums);
        for (int i : nums)
            System.out.print(i + "\t");
        System.out.println();
    }
}
