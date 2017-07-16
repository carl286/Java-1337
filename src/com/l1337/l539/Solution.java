package com.l1337.l539;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Solution {

//    https://leetcode.com/problems/minimum-time-difference/discuss/100694/7-liner-%22O(1)%22-solution:-only-60*24-possible-different-time-points!
    public int findMinDifference(List<String> timePoints) {
        TreeSet<Integer> set = new TreeSet<>();
        for (String s : timePoints) {
            int x = timeToInt(s);
            if (set.contains(x))
                return 0;
            set.add(x);
        }
        int round = 24*60;
        int ret = round;
        Iterator<Integer> iter = set.iterator();
        int pre = iter.next();

        while (iter.hasNext()) {
            int cur = iter.next();
            int diff = cur - pre;
            if (diff > 12*60) {
                ret = Math.min(ret, round - cur + pre);
            } else {
                ret = Math.min(ret, cur - pre);
            }
            pre = cur;
        }

        if (ret > 1) {
            pre = set.first();
            int cur = set.last();
            ret = Math.min(ret, Math.min(cur - pre, round - cur + pre));
        }

        return ret;
    }

    int timeToInt(String t) {
        String [] splits = t.split(":");
        return Integer.parseInt(splits[0]) * 60 + Integer.parseInt(splits[1]);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findMinDifference(Arrays.asList("01:01","02:01","03:00")));
    }
}
