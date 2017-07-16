package com.l1337.l544;


public class Solution {

//    https://leetcode.com/articles/output-contest-matches/
    public String findContestMatch(int n) {
        String tmp [] = new String [n+1];
        for (int i = 1; i <= n; ++i)
            tmp[i] = Integer.toString(i);

        while (n > 1) {
            //merge from 1 to n
            for (int first = 1, last = n; first < last; ++first, --last) {
                tmp[first] = "(" + tmp[first] + "," + tmp[last] + ")";
            }
            n >>= 1;
        }

        return tmp[1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findContestMatch(8));
    }
}
