package com.l1337.l520;


public class Solution {

//    https://leetcode.com/problems/detect-capital/discuss/99249/Python-has-useful-methods...
    public boolean detectCapitalUse(String word) {

        boolean startedWithCapital = Character.isUpperCase(word.charAt(0));
        char c = ' ';//U, L
        if (!startedWithCapital)
            c = 'L';
        for (int i = 1; i < word.length(); ++i) {
//            if (startedWithCapital) {
                if (c == ' ') {
                    if (Character.isUpperCase(word.charAt(i))) {
                        c = 'U';
                    } else {
                        c = 'L';
                    }
                } else {
                    if (Character.isUpperCase(word.charAt(i))) {
                        if (c == 'L')
                            return false;
                    } else {
                        if (c == 'U')
                            return false;
                    }
                }
                //can contain all lower or all upper
//            } else {
                //can contain only all lower

//            }
        }

        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.detectCapitalUse("ffffffffffffffF"));
    }
}
