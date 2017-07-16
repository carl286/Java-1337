package com.l1337.l69;

public class Solution {

    public int mySqrt(int x) {
         if (x <= 1)
            return x;
        // left <= x < right
        // f(left) <= target, f(right) > target
        int left = 0, right = x;
        while (left + 1 != right) {
            int mid = left + (right - left) / 2;
            if ( mid <= x / mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        for (int x = 0; x < 20; ++x)
            System.out.println(x + "\t" + s.mySqrt(x));
    }
}
