package com.l1337.l434;


public class Solution {

//    https://leetcode.com/problems/number-of-segments-in-a-string/description/
    public int countSegments(String s) {
        int ret = 0;
        int i = 0;
        while (i < s.length()) {
            boolean find = false;
            while (i < s.length() && !Character.isWhitespace(s.charAt(i))) {
                ++i;
                find = true;
            }

            if (find)
                ++ret;
            while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
                ++i;
            }
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.countSegments(" ABD sdf"));
    }
}
