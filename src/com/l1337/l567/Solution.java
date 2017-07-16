package com.l1337.l567;


public class Solution {

//    https://leetcode.com/problems/permutation-in-string/discuss/102588/Java-Solution-Sliding-Window
//    https://leetcode.com/problems/permutation-in-string/discuss/102590/8-lines-slide-window-solution-in-Java
    public boolean checkInclusion(String s1, String s2) {
        //assume both string length >= 1
        if (s1.length() > s2.length())
            return false;

        int dataMap [] = new int [26];
        for (int i = 0; i < s1.length(); ++i)
            ++dataMap[s1.charAt(i) - 'a'];

        int runningMap [] = new int [26];
        for (int start = 0, i = 0; i < s2.length(); ++i) {
            //visit s2[i]
            int index = s2.charAt(i) - 'a';
            ++runningMap[index];

            while (runningMap[index] > dataMap[index]) {
                --runningMap[s2.charAt(start) - 'a'];
                ++start;
            }

            if (i - start + 1 == s1.length())
                return true;
        }

        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.checkInclusion("ab", "eidboaoo"));
    }
}
