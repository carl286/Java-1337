package com.l1337.l163;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    // https://www.lintcode.com/problem/missing-ranges/
    //Missing Ranges
    /*
    Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper],
    return its missing ranges.
Example:
Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]

Input:
nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
Output:
["4->6"]
Explanation:
in range[0,7],the missing range include range[4,6]


//edge cases:
        int nums [] = new int [] {1, 1, 1};
        int lower = 1, upper = 1;

     */

//    https://wentao-shao.gitbook.io/leetcode/array/163.missing-ranges
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ret = new ArrayList<>();
        int tryToFindLeft = lower, i = 0;
        while (i < nums.length)
        {
            if (tryToFindLeft > nums[i])
            {
                ++i;
            }
            else{
                if (tryToFindLeft != nums[i])
                {
                    //there is a gap...tryToFindLeft and nums[i]-1
                    if (tryToFindLeft == nums[i]-1)
                    {
                        //a single entry
                        ret.add(Integer.toString(tryToFindLeft));
                    }
                    else
                    {
                        ret.add(Integer.toString(tryToFindLeft) + "->" + Integer.toString(nums[i]-1));
                    }
                }
                tryToFindLeft = nums[i] + 1;
                ++i;
            }
        }

        if (tryToFindLeft <= upper)
        {
            if (tryToFindLeft == upper)
            {
                //a single entry
                ret.add(Integer.toString(tryToFindLeft));
            }
            else
            {
                ret.add(Integer.toString(tryToFindLeft) + "->" + Integer.toString(upper));
            }
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int nums [] = new int [] {0, 1, 3, 50, 75};
//        int lower = 0, upper = 99;

//        int nums [] = new int [] {0, 1, 2, 3, 4,5,6,7};
//        int lower = 0, upper = 7;
        int nums [] = new int [] {1, 1, 1};
        int lower = 1, upper = 1;
        for (String ss : s.findMissingRanges(nums, lower, upper))
        {
            System.out.println(ss);
        }

    }
}
