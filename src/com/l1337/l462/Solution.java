package com.l1337.l462;


import java.util.Arrays;

public class Solution {

//    http://www.cnblogs.com/grandyang/p/6089060.html
    /*
    On the right direction, but not right enough
    public int minMoves2(int[] nums) {
        if (nums.length < 2)
            return 0;
        //assum no negative number for now.
        Long sum = 0l;
        for (int i : nums)
            sum += i;
        double avg = (double) sum / nums.length;
        int nearest = (int) avg;
        int less = 0, more = 0;
        int count = 0;
        for (int i : nums) {
            if (i < nearest) {
                less++;
                count += (nearest - i);
            }
            else if (i > nearest) {
                count += (i - nearest);
                more++;
            }
        }
        return Math.min(count, count + (nums.length - more - more));
    }
    */
public int minMoves2(int[] nums) {
    /*
    我们首先给数组排序，那么我们最终需要变成的相等的数字就是中间的数，如果数组有奇数个，那么就是最中间的那个数字；如果是偶数个，那么就是中间两个数的区间中的任意一个数字。而两端的数字变成中间的一个数字需要的步数实际上就是两端数字的距离，
     */

    Arrays.sort(nums);
    int i = 0, j = nums.length - 1;
    int ret = 0;
    while (i < j) {
        ret += nums[j--] - nums[i++];
    }

    return ret;
}
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.minMoves2(new int [] {1,0,0,8,6}));
    }
}
