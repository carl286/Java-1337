package com.l1337.l1340;


import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public int maxJumps(int[] arr, int d) {
//        int [] dp = new int [arr.length];
//
//        Stack<Integer> st = new Stack<>();
//        for(int i = 0; i < arr.length; ++i)
//        {
//            while (!st.isEmpty() && arr[st.peek()] < arr[i])
//            {
//                if (st.peek() >= i - d)
//                    dp[i] = Math.max(dp[i], 1 + dp[st.pop()]);
//                else
//                    st.pop();
//            }
//            st.push(i);
//        }
//
//        st.clear();
//        for(int i = arr.length - 1; i >= 0; --i)
//        {
//            while (!st.isEmpty() && arr[st.peek()] < arr[i])
//            {
//                if (st.peek() <= i + d)
//                    dp[i] = Math.max(dp[i], 1 + dp[st.pop()]);
//                else
//                    st.pop();
//            }
//            st.push(i);
//        }
//        int ret = 0;
//        for(int k = 0; k < arr.length; ++k)
//        {
//            ret = Math.max(ret, dp[k]);
//        }
//
//        return ret + 1;
        return -1;
    }

//    https://mp.weixin.qq.com/s/kEQ00_WLqDTG6tbsjQ2Xjw
    private int maxJumpsMemo(int [] arr, int index, int d, int [] memo)
    {
        if (memo[index] >= 0)
            return memo[index];

        int ret = 0;
        for(int k = 1; k <= d && index + k < arr.length; ++k)
        {
            if (arr[index] > arr[index + k])
            {
                ret = Math.max(ret, 1 + maxJumpsMemo(arr, index + k, d, memo));
            }
            else
            {
                break;
            }
        }

        for(int k = 1; k <= d && index - k >= 0; ++k)
        {
            if (arr[index] > arr[index - k])
            {
                ret = Math.max(ret, 1 + maxJumpsMemo(arr, index - k, d, memo));
            }
            else
            {
                break;
            }
        }

        memo[index] = ret;
        return memo[index];
    }
    public int maxJumpsMemo(int[] arr, int d) {
        int [] memo = new int [arr.length];
        Arrays.fill(memo,-1);
        int ret = 0;
        for(int k = 0; k < arr.length; ++k)
        {
            ret = Math.max(ret, maxJumpsMemo(arr, k, d, memo));
        }

        return ret + 1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.maxJumps(new int [] {6,4,14,6,8,13,9,7,10,6,12}, 2));
//        System.out.println(s.maxJumps(new int [] {7,1,7,1}, 2));
    }
}
