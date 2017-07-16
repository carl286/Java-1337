package com.l1337.l161;


public class Solution {

    /*
    https://www.lintcode.com/problem/one-edit-distance/description

    One Edit Distance
    Given two strings S and T, determine if they are both one edit distance apart.
One ediit distance means doing one of these operation:

insert one character in any position of S
delete one character in S
change one character in S to other character

Example
Example 1:

Input: s = "aDb", t = "adb"
Output: true
Example 2:

Input: s = "ab", t = "ab"
Output: false
Explanation:
s=t ,so they aren't one edit distance apart
     */
//    https://www.geeksforgeeks.org/check-if-two-given-strings-are-at-edit-distance-one/
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() + 1 < t.length() || s.length() - 1 > t.length())
            return false;

        int ss = 0, es = s.length() - 1, st = 0, et = t.length() - 1;

        while (ss <= es && st <= et && s.charAt(ss) == t.charAt(st))
        {
            ++ss;
            ++st;
        }

        while (ss <= es && st <= et && s.charAt(es) == t.charAt(et))
        {
            --es;
            --et;
        }

        if (t.length() + 1 == s.length())
        {
            //s much remove 1 character
            if (st > et && ss == es)
                return true;
        }
        else if (s.length() == t.length())
        {
            if (ss == es && st == et)
                return true;
        }
        else
        {
            //s must add 1 character
            if (ss > es && st == et)
                return true;
        }


        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.isOneEditDistance("", "a")); //T
        System.out.println(s.isOneEditDistance("ab", "ab")); //F
        System.out.println(s.isOneEditDistance("a", "c")); //T
        System.out.println(s.isOneEditDistance("aDb", "adb")); //T
        System.out.println(s.isOneEditDistance("geeks", "geek")); //T
        System.out.println(s.isOneEditDistance("geek", "geeks")); //T
        System.out.println(s.isOneEditDistance("geeks", "geeks")); //F
        System.out.println(s.isOneEditDistance("geaks", "geeks")); //T
        System.out.println(s.isOneEditDistance("peaks", "geeks")); //F

    }
}
