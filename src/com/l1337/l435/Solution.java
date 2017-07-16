package com.l1337.l435;


import java.util.Arrays;
import java.util.Comparator;

import com.l1337.common.Interval;

public class Solution {
    private boolean isNotOverlap(Interval a, Interval b) {
        return (a.start >= b.end || b.start >= a.end);
    }

//    https://leetcode.com/problems/non-overlapping-intervals/discuss/91713/Java:-Least-is-Most
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length <= 1)
            return 0;

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                if (a.start < b.start)
                    return -1;
                else if (a.start > b.start)
                    return +1;
                else if (a.end < b.end)
                    return -1;
                else if (a.end > b.end)
                    return +1;
                return 0;
            }
        });

        int ret = 0, i = 1;
        Interval cur = intervals[0];
        while (i < intervals.length) {
            if (isNotOverlap(cur, intervals[i])) {
                cur = intervals[i];
            } else {
                ++ret;
                if (cur.end > intervals[i].end)
                    cur = intervals[i];
            }

            ++i;
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
