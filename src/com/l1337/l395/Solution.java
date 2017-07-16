package com.l1337.l395;


import java.util.*;

public class Solution {

//    https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/submissions/
    public int longestSubstring(String s, int k) {
        if (k > s.length())
            return 0;
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            int map [] = new int [26];
            int index_map = 0x0;
            for (int j = i; j < s.length(); ++j) {
                int index = s.charAt(j) - 'a';
                ++map[index];
                if (map[index] < k)
                    index_map |= (1 << index);
                else
                    index_map &= ~(1 << index);
                if (index_map == 0)
                    ret = Math.max(ret, j - i + 1);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String ss = "aaabb";
        int k = 3;
//        String ss = "abbbbbbcaa";
//        int k = 3;
        System.out.println(s.longestSubstring(ss, k));
    }
}
