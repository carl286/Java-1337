package com.l1337.l624;


public class Solution {

//    https://leetcode.com/articles/maximum-distance-in-array/
    public int maxDistance(int[][] list) {
        int cur_min = list[0][0], cur_max = list[0][list[0].length - 1];

        int ret = 0;

        for (int i = 1; i < list.length; ++i) {
            int next_min = list[i][0], next_max = list[i][list[i].length - 1];
            ret = Math.max(ret, Math.max(Math.abs(cur_max - next_min), Math.abs(cur_min - next_max)));
            cur_min = Math.min(next_min, cur_min);
            cur_max = Math.max(next_max, cur_max);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [][] list = new int[][] {{1,2,3}, {4,5}, {1,2,3}};
        System.out.println(s.maxDistance(list));
    }
}
