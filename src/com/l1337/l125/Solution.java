package com.l1337.l125;


public class Solution {

    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while ( i < j)
        {
            while (i < j && !(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))) )
                ++i;
            while (i < j && !(Character.isLetter(s.charAt(j)) || Character.isDigit(s.charAt(j))) )
                --j;

            if (i < j)
            {
                if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j)))
                {
                    ++i;
                    --j;
                }
                else
                {
                    return false;
                }
            }
        }

        return i >= j;
    }

    public boolean isPalindrome2(String s) {
        int i = 0, j = s.length()-1;
        while(i < j)
        {
            while(i < j && !(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))))
                ++i;
            while(i < j && !(Character.isLetter(s.charAt(j)) || Character.isDigit(s.charAt(j))))
                --j;
            if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
            ++i;
            --j;
        }

        return true;

    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.isPalindrome2("race a car"));
        System.out.println(s.isPalindrome2("0P"));
    }
}
