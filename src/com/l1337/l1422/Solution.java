package com.l1337.l1422;


public class Solution {

    public int maxScore(String s) {
        int ret = 0;
        int ones = 0, zeros = 0;
        for (int i = 0; i < s.length(); ++i)
            if (s.charAt(i) == '1')
                ++ones;

        for (int i = 0; i + 1< s.length(); ++i)
        {
            if (s.charAt(i) == '0')
            {
                ++zeros;
            }
            else
            {
                --ones;
            }

            ret = Math.max(ret, zeros + ones);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.maxScore("011101"));
    }
}
