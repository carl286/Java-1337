package com.l1337.l443;


public class Solution {

//    https://leetcode.com/problems/string-compression/description/
    public int compress(char[] chars) {
        int ret = 0;
        int i = 0, k = 0;
        while (i < chars.length) {
            int j = i;
            while (j < chars.length && chars[i] == chars[j])
                ++j;
            int count = j - i;
            chars[k++] = chars[i];
            ++ret;
            if (count > 1) {
                String ct = Integer.toString(count);
                ret += ct.length();
                for (int t = 0; t < ct.length(); t++)
                    chars[k++] = ct.charAt(t);
            }
            System.out.println(count);

            i = j;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.compress(new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
    }
}
