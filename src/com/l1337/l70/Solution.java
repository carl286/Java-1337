package com.l1337.l70;

public class Solution {

    public int climbStairs(int n) {

        //0 1 2
        //1 1 2
        int n1 = 1, n2 = 1;
        for (int i = 2; i <= n; ++i) {
            int tmp = n2;
            n2 = n1 + n2;
            n1 = tmp;
        }
        return n2;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        for (int x = 0; x <= 10; ++x)
        System.out.println(s.climbStairs(x));
    }
}
