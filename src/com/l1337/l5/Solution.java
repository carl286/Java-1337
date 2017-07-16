package com.l1337.l5;


public class Solution {

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1)
            return s.substring(0, length);
        int maxStart = length - 1, maxLength = 1;
        boolean [] map = new boolean[length];
        map[length-1] = true;
        for (int j = length - 1; j >= 0; --j)
        {
            //init
            for (int i = length - 1; i >= j; --i)
            {
                map[i] = (i - j + 1) > 2 ? (s.charAt(i) == s.charAt(j) && map[i - 1]) : s.charAt(i) == s.charAt(j);
                if (i - j + 1 > maxLength && map[i])
                {
                    maxStart = j;
                    maxLength = i - j + 1;
                }
            }
            //map[j] = true;
            /*
            for (int k = 0; k < length; ++k)
                System.out.print( map[k] + "\t");
            System.out.println();

             */
        }

        return s.substring(maxStart, maxStart + maxLength);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String test = "aa";

        System.out.println(s.longestPalindrome(test));
    }
}
