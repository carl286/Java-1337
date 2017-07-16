package com.l1337.l452;


import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    private boolean isOverlapped(int [] p1, int [] p2) {
        return !((p1[1] < p2[0]) || (p2[1] < p1[0]));
    }

//    https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/discuss/93703/Share-my-explained-Greedy-solution-as-the-highest-voted-java-solution-right-now-is-not-ideal
    public int findMinArrowShots(int[][] points) {

        if (points.length <= 1)
            return points.length;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int counter = 0;
        int start = 0, cur = 0;
        while (cur < points.length) {
            if (isOverlapped(points[start], points[cur])) {
                ++cur;
            } else {
                ++counter;
                start = cur++;
            }
        }

        return counter + 1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
