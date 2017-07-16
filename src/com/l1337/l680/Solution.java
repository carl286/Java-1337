package com.l1337.l680;


public class Solution {

//    https://leetcode.com/problems/valid-palindrome-ii/discuss/171564/Java-Easy-to-understand-recursive-solution
    public boolean validPalindrome(String s) {
        int length = s.length();
        if (length <= 2)
            return true;

        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            ++i;
            --j;
        }

        if (i >= j)
            return true;

        //drop i or j
        int i2 = i, j2 = j;
        ++i2;
        while (i2 < j2 && s.charAt(i2) == s.charAt(j2)) {
            ++i2;
            --j2;
        }
        if (i2 >= j2)
            return true;

        i2 = i;
        j2 = j;
        --j2;
        while (i2 < j2 && s.charAt(i2) == s.charAt(j2)) {
            ++i2;
            --j2;
        }
        return  (i2 >= j2);
    }

























    public boolean validPalindromeApril7_21(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j)
        {
            if (s.charAt(i) == s.charAt(j))
            {
                ++i;
                --j;
            }
            else
            {
                break;
            }
        }

        if (i >= j)
            return true;
        int l = i + 1, r = j;
        while (l < r)
        {
            if (s.charAt(l) == s.charAt(r))
            {
                ++l;
                --r;
            }
            else
                break;
        }
        if (l >= r)
            return true;
        l = i;
        r = j - 1;

        while (l < r)
        {
            if (s.charAt(l) == s.charAt(r))
            {
                ++l;
                --r;
            }
            else
                break;
        }
        if (l >= r)
            return true;

        return false;
    }


    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.validPalindrome("abca"));
    }
}
