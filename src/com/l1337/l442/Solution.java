package com.l1337.l442;


import java.util.ArrayList;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
//    https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92392/Very-simple-C++-solution


//    https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92387/Java-Simple-Solution
//    https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92461/C++-simple-solution-leveraging-the-highest-bit
public List<Integer> findDuplicates2(int[] nums) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < nums.length; ++i) {
        int index = Math.abs(nums[i])-1;
        if (nums[index] < 0)
            res.add(Math.abs(index+1));
        nums[index] = -nums[index];
    }
    return res;
}

    private void swap(int [] nums, int i, int j) {
        int k = nums[i];
        nums[i] = nums[j];
        nums[j] = k;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            while(nums[i] != i + 1 && nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1)
                ret.add(nums[i]);
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        for (int i : s.findDuplicates(new int [] {4,3,2,7,8,2,3,1}))
            System.out.println(i);
    }
}
