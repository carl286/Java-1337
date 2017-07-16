package com.l1337.l165;


public class Solution {

//    https://leetcode.com/submissions/detail/62348848/
    // Please try to understand the problem....
    public int compareVersion(String version1, String version2) {
        String [] s1 = version1.split("\\.");
        String [] s2 = version2.split("\\.");
//        System.out.println(s1.length);
//        for (String ss1: s1
//             ) {
//            System.out.println(ss1);
//        }
//
//        for (String ss2: s2
//        ) {
//            System.out.println(ss2);
//        }

        int i = 0;
        while (i < s1.length && i < s2.length)
        {
            //skip leading 0
            int j = 0, k = 0;
            while (j < s1[i].length() && s1[i].charAt(j) == '0')
                ++j;
            while (k < s2[i].length() && s2[i].charAt(k) == '0')
                ++k;
            while (j < s1[i].length() && k < s2[i].length() && s1[i].charAt(j) == s2[i].charAt(k))
            {
                ++j;
                ++k;
            }
            if (j == s1[i].length())
            {
                if (k == s2[i].length())
                {
                    ++i;
                }
                else
                {
                    return -1;
                }
            }
            else
            {
                if (k == s2[i].length())
                    return +1;
                else
                {
                    if (s1[i].length() - j == s2[i].length() - k)
                    {
                        return s1[i].charAt(j) < s2[i].charAt(k) ? -1 : 1;
                    }
                    else
                    {
                        return (s1[i].length() - j < s2[i].length() - k) ? -1 : +1;
                    }

                }
            }
        }

        //at least one of them reaches the end...
        while (i < s1.length)
        {
            int j = 0;
            while (j < s1[i].length() && s1[i].charAt(j) == '0')
                ++j;
            if (j == s1[i].length())
                ++i;
            else
                return +1;
        }

        while (i < s2.length)
        {
            int k = 0;
            while (k < s2[i].length() && s2[i].charAt(k) == '0')
                ++k;
            if (k == s2[i].length())
                ++i;
            else
                return -1;
        }

        return 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.compareVersion("1.2", "1.10"));
    }
}
