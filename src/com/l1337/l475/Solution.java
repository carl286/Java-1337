package com.l1337.l475;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/heaters/discuss/95886/Short-and-Clean-Java-Binary-Search-Solution
    public int findRadius(int[] houses, int[] heaters) {
        if (houses.length == 0)
            return 0;
        if (heaters.length == 0)
            return Integer.MAX_VALUE;

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int ret = Integer.MIN_VALUE;
        int i = 0, j = 0;
        //what's your loop invariant here????
        while (i < houses.length && j < heaters.length) {
            int local_min = Integer.MAX_VALUE;
            while (j < heaters.length && houses[i] >= heaters[j]) {
                local_min = (houses[i] - heaters[j]);
                ++j;
            }
            if (j < heaters.length)
                local_min = Math.min(local_min, heaters[j] - houses[i]);
            if (j > 0)
                --j;
            ret = Math.max(ret, local_min);
            ++i;
            while (i + 1< houses.length && houses[i] == houses[i+1])
                ++i;
        }

        return ret;
    }

//    https://leetcode.com/problems/heaters/description/
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findRadius(new int[]{1,2,3,4}, new int[]{1,4}));
    }
}
