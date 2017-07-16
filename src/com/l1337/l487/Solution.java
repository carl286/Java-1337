package com.l1337.l487;


public class Solution {

    /*
    Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

    Example 1:

    Input: [1,0,1,1,0]
    Output: 4
    Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
        After flipping, the maximum number of consecutive 1s is 4.
    Note:

    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000

    Follow up:
What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     */
    public int findMaxConsecutiveOnesMarch10_21(int[] nums) {
        int first = 0, second = 0, ret = 0;
        boolean seenZero = false;
        for(int i = 0; i < nums.length; ++i)
        {
            if (nums[i] == 1)
            {
                if (seenZero)
                {
                    ++second;
                }
                else
                {
                    ++first;
                }
            }
            else
            {
                if (seenZero)
                {
                    if (i > 0 && nums[i-1] == 0)
                    {
                        first = 0;
                        second = 0;
                        seenZero = false;
                    }
                    else
                    {
                        first = second;
                        second = 0;
                    }
                }
                else
                {
                    seenZero = true;
                }

            }
            ret = Math.max(ret, first + second + (seenZero ? 1 : 0));
        }
        return ret;
    }


//    https://blog.csdn.net/magicbean2/article/details/78690677
    public int findMaxConsecutiveOnes(int[] nums) {
        int last_ones = 0, cur_ones = 0, ret = 0;
        int i = 0;
        while (i < nums.length) {
            int j = i;
            boolean has_zeros = false;
            while (j < nums.length && nums[j] == 0) {
                ++j;
                has_zeros = true;
            }
            if (j > i + 1) {
                last_ones = 0;
            }

            i = j;
            cur_ones = 0;
            while (i < nums.length && nums[i] == 1) {
                ++i;
                ++cur_ones;
            }

            ret = Math.max(ret, has_zeros ? 1 + last_ones + cur_ones : cur_ones);
            last_ones = cur_ones;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findMaxConsecutiveOnesMarch10_21(new int []{1,0,1,1,0}));
    }
}
