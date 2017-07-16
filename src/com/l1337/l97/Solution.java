package com.l1337.l97;


public class Solution {

    // https://leetcode.com/submissions/detail/62778009/
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        //use character set...
        boolean [][][] map = new boolean[s1.length() + 1][s2.length() + 1][s3.length() + 1];

        //init
        /*
        map[0][0][0] = true;

        for (int i = 0; i < s1.length(); ++i)
        {
            if (s1.charAt(i) == s3.charAt(i))
            {
                map[i+1][0][i+1] = true;
            }
            else
            {
                break;
            }
        }

        for (int i = 0; i < s2.length(); ++i)
        {
            if (s2.charAt(i) == s3.charAt(i))
            {
                map[0][i+1][i+1] = true;
            }
            else
            {
                break;
            }
        }
        */
        map[0][1][1] = s2.charAt(0) == s3.charAt(0);
        map[1][0][1] = s1.charAt(0) == s3.charAt(0);
        for (int l = 2; l <= s1.length() && map[l-1][0][l-1]; l++)
            map[l][0][l] = s1.charAt(l-1) == s3.charAt(l-1);
        for (int l = 2; l <= s2.length() && map[0][l-1][l-1]; l++)
            map[0][l][l] = s2.charAt(l-1) == s3.charAt(l-1);


        for (int l = 2; l <= s3.length(); ++l)
        {
            for (int l1 = 1; l1 < l && l1 <= s1.length(); ++l1 )
            {
                if (l - l1 <= s2.length())
                {
                    map[l1][l-l1][l] = (s1.charAt(l1-1) == s3.charAt(l-1) && map[l1-1][l-l1][l-1]) ||
                            (s2.charAt(l-l1-1) == s3.charAt(l-1) && map[l1][l-l1-1][l-1]);

//                    if (map[l1][l-l1][l])
//                        break;
                }
            }
        }

        //print for comparison

        return map[s1.length()][s2.length()][s3.length()];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
         String s1 = "aabc", s2 = "abad", s3 = "aabacbad";
        //String s1 = "bc", s2 = "abad", s3 = "bacbad";
        // String s1 = "ac", s2 = "b", s3 = "abc";
        //String s1 = "ac", s2 = "b", s3 = "abc";
        System.out.println(s.isInterleave(s1,s2,s3));
    }
}
