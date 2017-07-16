package com.l1337.l56;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    private boolean isOverlap(int []a, int []b)
    {
        return !(a[1] < b[0] || b[1] < a[0]);
    }

    // https://leetcode.com/problems/merge-intervals/discuss/21452/Share-my-interval-tree-solution-no-sorting
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<int []> tmp = new ArrayList<>();
        int [] cur = intervals[0];
        for (int i = 1; i < intervals.length; ++i) {
            if (isOverlap(cur, intervals[i])) {
                cur[0] = Math.min(cur[0], intervals[i][0]);
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                tmp.add(cur);
                cur = intervals[i];
            }
        }

        tmp.add(cur);
        int [][] ret = new int[tmp.size()][2];
        for (int i = 0; i < ret.length; ++i)
            ret[i] = tmp.get(i);

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
