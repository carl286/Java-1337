package com.l1337.l300;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//http://www.cnblogs.com/grandyang/p/4938187.html
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null)
            return 0;
        int length = nums.length;
        if (length <= 1)
            return length;
        List<Integer> tmp = new ArrayList<>();
        tmp.add(nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            int index = Collections.binarySearch(tmp, nums[i]);
            if (index < 0) {
                index = -(1 + index);
                if (index < tmp.size())
                    tmp.set(index, nums[i]);
                else
                    tmp.add(nums[i]);
            }
//            System.out.println(nums[i] + "\t" + tmp.size());
        }
        return tmp.size();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int nums [] = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(s.lengthOfLIS(nums));
    }
}
