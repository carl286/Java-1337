package com.l1337.l907;


import java.nio.file.StandardWatchEventKinds;
import java.util.Stack;

public class Solution {

    private int MODULO_BASE = 1000000000+7;

//    https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170750/C++JavaPython-Stack-Solution
    public int sumSubarrayMins(int[] arr) {
        // first submittion, memory exceed limit
        /*
        int [][] dp = new int [arr.length][arr.length];
        int ret = 0;
        for (int i = dp.length - 1; i >= 0; --i)
        {
            //update dp[i][j];
            for (int j = i; j < dp.length; ++j)
            {
                dp[i][j] = i == j ? arr[i] : Math.min(dp[i][i], dp[i+1][j]);
                ret = (ret + dp[i][j]) % MODULO_BASE;
            }
        }
         */

        //second submission, Time limit exceeded....
//        int []dp = new int [arr.length];
//        int ret = 0;
//        for (int i = dp.length - 1; i >= 0; --i)
//        {
//            //update dp[i][j];
//            for (int j = i; j < dp.length; ++j)
//            {
//                dp[j] = i == j ? arr[i] : Math.min(arr[i], dp[j]);
//                ret = (ret + dp[j]) % MODULO_BASE;
//            }
//        }

        int []dp = new int [arr.length]; //sum of subarray minimums, ending in i
//        int [] data = new int [arr.length];
//        int size = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; ++i)
        {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i])
            {
                st.pop();
            }

            dp[i] = st.isEmpty() ? (arr[i] * (i + 1)) %MODULO_BASE : ((arr[i] * (i - st.peek())) %MODULO_BASE + dp[st.peek()]) % MODULO_BASE;
            st.push(i);
        }

        int ret = 0;
        for(int i = 0; i < dp.length; ++i)
        {
            ret = (ret + dp[i]) % MODULO_BASE;
        }
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.sumSubarrayMins(new int [] {3,1,2,4}));
    }
}
