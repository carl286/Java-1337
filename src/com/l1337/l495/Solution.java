package com.l1337.l495;


public class Solution {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length <= 0 || duration <= 0)
            return 0;

        int ret = duration;
        int end = timeSeries[0] + duration;

        for (int i = 1; i < timeSeries.length; ++i) {
            int old_end = end;
            end = timeSeries[i] + duration;
            if (old_end > timeSeries[i]) {
                ret += end - old_end;
            } else {
                //else, nothing to merge
                ret += duration;
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findPoisonedDuration(new int [] {1,2}, 2));
    }
}
