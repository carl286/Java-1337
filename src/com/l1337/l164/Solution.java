package com.l1337.l164;


import java.util.Arrays;

public class Solution {

    //https://leetcode.com/submissions/detail/62344916/
    //how to make it O(n)
    // https://leetcode.com/problems/maximum-gap/solution/
    //Radix Sort, even radix sort, you can use base 2, 10 and etc...
    //Buckets and The Pigeonhole Principle
        // how to compare between buckets, log in the min/max when adding to the bucket..

    private void swap(int [] nums, int i, int j)
    {
        if (i != j)
        {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    public int maximumGap(int[] nums) {
        if (nums.length <= 1)
            return 0;

        // Arrays.sort(nums);
        //binary sort
        //radix sort

        //see the solution how to do radix sort with Count sort
        int [] aux = new int [nums.length];
        for (int i = 0; i < 32; i++)
        {
            int mask = 1 << i;
            int cnt = 0;
            for (int j = 0; j < nums.length; ++j)
            {
                if ((nums[j] & mask) == 0)
                    cnt++;
            }

            int a0 = 0;
            for (int j = 0; j < nums.length; ++j)
            {
                if ((nums[j] & mask) == 0)
                {
                    aux[a0++] = nums[j];
                }
                else
                {
                    aux[cnt++] = nums[j];
                }
            }
            System.arraycopy(aux, 0, nums, 0, nums.length);
        }

//        for (int i = 0; i < nums.length; ++i)
//            System.out.print(nums[i] + "\t");

        int ret = 0;
        for (int i = 1; i < nums.length; ++i)
            ret = Math.max(ret, nums[i] - nums[i-1]);

        return ret;
    }


    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.maximumGap(new int [] {3,6,9,1}));
    }
}
