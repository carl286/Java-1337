package com.l1337.l360;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
题目链接: https://leetcode.com/problems/sort-transformed-array/

Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]

 */
public class Solution {

    int f(int a, int b, int c, int x) {
        return a * x * x + b * x + c;
    }

    List<Integer> sortTransformedArray(List<Integer> nums, int a, int b, int c) {
        List<Integer> ret = new ArrayList<>();
        if (a != 0) {
            // x < -b/2a
            List<Integer> left = new ArrayList<>();
            // x >= -b/2a
            List<Integer> right = new ArrayList<>();

            double mid = - (double)b/(2*a);
            for (Integer i : nums) {
                if (i < mid)
                    left.add(f(a,b,c,i));
                else
                    right.add(f(a,b,c,i));
            }

            if (a > 0)
                Collections.reverse(left);
            else
                Collections.reverse(right);

            //merge left and right
            int i = 0, j = 0;
            while (i < left.size() && j < right.size()) {
                if (left.get(i) < right.get(j)) {
                    ret.add(left.get(i++));
                } else {
                    ret.add(right.get(j++));
                }
            }

            ret.addAll(left.subList(i, left.size()));
            ret.addAll(right.subList(j, right.size()));
        } else {
            for (Integer i : nums)
                ret.add(f(a,b,c,i));
            if (b < 0)
                Collections.reverse(ret);
        }


        //if a == 0, then this is one dimentions....
        return ret;
    }

    //One array is enough
//    思路: 当a>0时,抛物线开口向上,这样给定的数组最大值肯定是在两端, 也就是要么在数组开始,要么在数组的最后, 这样我们可以依次取得最大值.最后的时候翻转一下数组即可.
//            当a<0时, 抛物线开口向下,这样最小值肯定也是在两端, 我们可以依次在两端取最小值.
    public static void main(String [] args) {
        Solution s = new Solution();
        Integer [] nums = {-4, -2, 2, 4};
//        int a = 1, b = 3, c = 5;
        int a = -1, b = 3, c = 5;

        for (Integer i : s.sortTransformedArray(Arrays.asList(nums),a,b,c))
            System.out.println(i);
    }
}
