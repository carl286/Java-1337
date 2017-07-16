package com.l1337.l699;


import java.util.ArrayList;
import java.util.List;

public class Solution {
    private class Interval {
    int start, end, height;
     Interval(int start, int end, int height) {
        this.start = start;
        this.end = end;
        this.height = height;
    }
}
    public List<Integer> fallingSquares(int[][] positions) {
//        List<Interval> intervals = new ArrayList<>();
//        List<Integer> res = new ArrayList<>();
//        int h = 0;
//        for (int[] pos : positions) {
//            Interval cur = new Interval(pos[0], pos[0] + pos[1] - 1, pos[1]);
//            h = Math.max(h, getHeight(intervals, cur));
//            res.add(h);
//        }
//        return res;
        int[] qans = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int left = positions[i][0];
            int size = positions[i][1];
            int right = left + size;
            qans[i] += size;

            for (int j = i+1; j < positions.length; j++) {
                int left2 = positions[j][0];
                int size2 = positions[j][1];
                int right2 = left2 + size2;
                if (left2 < right && left < right2) { //intersect
                    qans[j] = Math.max(qans[j], qans[i]);
                }
            }
        }

        List<Integer> ans = new ArrayList();
        int cur = -1;
        for (int x: qans) {
            cur = Math.max(cur, x);
            ans.add(cur);
        }
        return ans;
    }
    private int getHeight(List<Interval> intervals, Interval cur) {
        int preMaxHeight = 0;
        for (Interval i : intervals) {
            // Interval i does not intersect with cur
            if (i.end < cur.start) continue;
            if (i.start > cur.end) continue;
            // find the max height beneath cur
            preMaxHeight = Math.max(preMaxHeight, i.height);
        }
        cur.height += preMaxHeight;
        intervals.add(cur);
        return cur.height;
    }

//    https://leetcode.com/articles/falling-squares/
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.fallingSquares(new int [][]{{1, 5}, {2,4}, {3,1}}));
    }
}
