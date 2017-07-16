package com.l1337.l650;


public class Solution {

//    https://leetcode.com/problems/2-keys-keyboard/discuss/105897/Loop-best-case-log(n)-no-DP-no-extra-space-no-recursion-with-explanation
    public int minSteps(int n) {
        int ret = 0;
        while (n > 1) {
            if ((n & 0x01) == 0) {
                ret += 2;
                n /= 2;
            } else {
                int mid = (int) Math.sqrt(n);
                int start;
                for (start = 3; start <= mid; ++start) {
                    if (n % start == 0)
                        break;
                }
                if (n % start != 0) {
                    //take n, it's a prime....
                    ret += n;
                    n = 1;
                } else {
                    ret += start;
                    n /= start;
                }
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.minSteps(27));
    }
}
