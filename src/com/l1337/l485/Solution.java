package com.l1337.l485;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int findMaxConsecutiveOnesI(int[] nums) {
        int ret = 0;
        for (int sum = 0, i = 0; i < nums.length; ++i)
        {
            if (nums[i] == 0)
            {
                sum = 0;
            }
            else
            {
                ++sum;
            }
            ret = Math.max(ret, sum);
        }

        return ret;
    }

    /*
    leetcode 487
    Problem:
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:

Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     */
    public int findMaxConsecutiveOnesII(int[] nums) {
        if (nums.length <= 0)
            return 0;
        List<Integer> tmp = new ArrayList<>();
        int acc = 0;

        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[i] == 0)
            {
                tmp.add(0);
            }
            else
            {
                ++acc;
                if (i == nums.length || nums[i+1] == 0)
                {
                    tmp.add(acc);
                    acc = 0;
                }
            }
        }


        //scan through the tmp
        acc = 0;
        int local = 0;
        int localZeros = 0;//may not necessary here....
        for (int start = 0, i = 0, zeroCounts = 0; i < tmp.size(); ++i)
        {
            if (tmp.get(i) == 0)
            {
                ++zeroCounts;
            }
            else
            {
                acc += tmp.get(i);
            }

            while (zeroCounts > 1)
            {
                if (tmp.get(start++) == 1)
                {
                    --acc;
                }
                else
                {
                    --zeroCounts;
                }
            }

            if (local <= acc)
            {
                local = acc;
                localZeros = Math.max(localZeros, zeroCounts);
            }
        }

        return localZeros == 0 ? local : (local + 1);
    }

//    https://leetcode.com/problems/max-consecutive-ones-iii/
//    https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/javacpython-sliding-window/379427?page=3
    public int findMaxConsecutiveOnesIII(int[] nums, int K) {
        if (nums.length <= 0)
            return 0;

        int ret = 0;

        for (int start = 0, i = 0, zeroCounts = 0; i < nums.length; ++i)
        {
            if (nums[i] == 0)
            {
                ++zeroCounts;
            }

            while (zeroCounts > K)
            {
                if (nums[start++] == 0)
                {
                    --zeroCounts;
                }
            }

            ret = Math.max(ret, i - start + 1);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findMaxConsecutiveOnesII(new int []{1,0,1,1,0}));
    }
}
