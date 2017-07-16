package com.l1337.l848;


public class Solution {

//    https://leetcode.com/problems/shifting-letters/discuss/137906/C%2B%2BJavaPython-Easy-Understood
    public String shiftingLetters(String S, int[] shifts) {
        int last = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = shifts.length - 1; i >= 0; --i) {
            shifts[i] = (shifts[i] + last) % 26;
            sb.append((char)(S.charAt(i) + shifts[i]) <= 'z' ? (char)(S.charAt(i) + shifts[i]) : (char)(S.charAt(i) + shifts[i] - 26));
            last = shifts[i];
        }

        sb.reverse();
        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.shiftingLetters("abc", new int [] {3,5,9}));
    }
}
