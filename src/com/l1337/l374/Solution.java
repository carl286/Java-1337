package com.l1337.l374;


public class Solution {

//    https://leetcode.com/problems/guess-number-higher-or-lower/
    int guess(int num) {
        return -1;
    }

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            int ret = guess(mid);
            if (ret == 0)
                return mid;
            if (ret < 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
