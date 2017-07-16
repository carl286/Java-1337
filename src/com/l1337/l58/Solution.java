package com.l1337.l58;


public class Solution {
    // https://leetcode.com/problems/length-of-last-word/discuss/22280/It-was-accepted...
    public int lengthOfLastWord(String s) {
        int ret = 0;
        /*
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && Character.isSpaceChar(s.charAt(i)))
                ++i;
            int j = i;
            while (i < s.length() && !Character.isSpaceChar(s.charAt(i)))
                ++i;
            ret = (i - j) != 0 ? (i - j) : ret;
        }

        return ret;
         */
        int i = 0, j = -1;
        while (i < s.length()) {
            if (Character.isSpaceChar(s.charAt(i))) {
                j = i;
            } else {
                ret = i - j;
            }

            ++i;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
