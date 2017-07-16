package com.l1337.l87;


import com.l1337.common.ListNode;

public class Solution {

    // https://www.jiuzhang.com/problem/scramble-string/
    // https://www.cnblogs.com/grandyang/p/4318500.html
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null)
        {
            return s1 == null && s2 == null;
        }
        if (s1.length() != s2.length())
        {
            return false;
        }

        //what if both empty????
        int length = s1.length();
        boolean [][][][] map = new boolean[length][length+1][length][length+1];
        map[0][0][0][0] = true; // to handle empty string, is there any other case that we need handle...

        for (int l = 1; l <= length; l++)
        {
            for (int i = 0; i + l <= length; ++i)
            {
                for (int j = 0; j + l <= length; ++j)
                {
                    if (l == 1) {
                        // we may use init oters to be true to avoid this
                        map[i][l][j][l] = s1.charAt(i) == s2.charAt(j);
                    } else
                    {
                        for (int k = 1; k < l && !map[i][l][j][l]; ++k)
                        {
                            map[i][l][j][l] = (map[i][k][j][k] && map[i+k][l-k][j+k][l-k]) ||
                                    (map[i][k][j+l-k][k] && map[i+k][l-k][j][l-k]);
                        }
                    }
                }
            }
        }

        return map[0][length][0][length];
    }
    
    public static void main(String [] args) {
        Solution s = new Solution();
        //String s1 = "ab", s2 = "ba";
        //String s1 = "great", s2 = "rgeat";
        String s1 = "abcde", s2 = "caebd";
        System.out.println(s.isScramble(s1,s2));
    }
}
