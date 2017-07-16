package com.l1337.l696;


public class Solution {

//    https://leetcode.com/problems/count-binary-substrings/discuss/108600/Java-O(n)-Time-O(1)-Space
    public int countBinarySubstrings(String s) {
        int ret = 0;
        
        int length = s.length();
        for (int i = 0; i < length - 1; ++i) {
            int l = 0;
            if (s.charAt(i) != s.charAt(i+1)) {
                //first one is always valid
                while (i - l >= 0 &&
                        i + 1 + l < length &&
                        s.charAt(i-l) == s.charAt(i) &&
                        s.charAt(i+1+l) == s.charAt(i+1)) {

                    ++ret;
                    ++l;
                }
            }

        }


        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.countBinarySubstrings("00110011"));
        System.out.println(s.countBinarySubstrings("10101"));

    }
}
