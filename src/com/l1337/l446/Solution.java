package com.l1337.l446;


import java.util.HashMap;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/arithmetic-slices-ii-subsequence/description/

//    There more to optimize it.
    public int numberOfArithmeticSlices(int[] a) {
        if (a.length < 3)
            return 0;
        Map<Long, Integer> [] dp= new Map [a.length];
        for (int i = 0; i < a.length; ++i) {
            dp[i] = new HashMap<>();
        }

        int sum = 0;
        for (int i = 1; i < a.length; ++i) {
            for (int j = 0; j < i; ++j) {
                long d = (long)a[i] - a[j];
                if (dp[j].containsKey(d)) {
                    if (dp[i].containsKey(d)) {
                        dp[i].put(d, 1 + dp[j].get(d) + dp[i].get(d));
                    } else {
                        dp[i].put(d, 1 + dp[j].get(d));
                    }
                } else {
                    //it becomes tricky here
                    //think about case 0,0,0,0,0,1,2. Though 1 ending with 0 seq
                    // However, when 2 comes, the count should be 5 for 0,1,2
                    if (dp[i].containsKey(d)) {
                        dp[i].put(d, 1 + dp[i].get(d));
                    } else {
                        dp[i].put(d, 1);
                    }
                    //only seq counted is a[j], a[i] in this case
                }
            }
            for(Integer v : dp[i].values()) {
                sum += v;
            }
            sum -= (i);
        }
        return sum;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World X");
//        int A [] = {2,4,6,8,10};
        int B [] = {0,2000000000,-294967296};
        System.out.println(s.numberOfArithmeticSlices(B));
//        must be at least of length 3 of the slice
//   it's acutally counting consective sequences
//
//
//
//
    }
}
