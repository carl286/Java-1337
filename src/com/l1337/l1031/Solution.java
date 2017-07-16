package com.l1337.l1031;


public class Solution {

//    https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/278251/JavaC%2B%2BPython-O(N)Time-O(1)-Space
//    https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/278727/C%2B%2B-O(N)-buysell-stock-2-times
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int n = A.length;
        int [][] first = new int [2][n];
        int [][] last = new int [2][n];

        for (int accL = 0, accM = 0, i = 0; i < n; ++i)
        {
            accL += A[i];
            if (i + 1 >= L)
            {
                first[0][i] = Math.max(accL, i-1 >= 0 ? first[0][i-1] : 0);
                accL -= A[i-L+1];
            }

            accM += A[i];
            if (i + 1 >= M)
            {
                first[1][i] = Math.max(accM, i-1 >= 0 ? first[1][i-1] : 0);
                accM -= A[i-M+1];
            }
        }

        for (int accL = 0, accM = 0, i = n - 1; i >= 0; --i)
        {
            accL += A[i];
            if (n - i >= L)
            {
                last[0][i] = Math.max(accL, i+1 < n ? last[0][i+1] : 0);
                accL -= A[i+L-1];
            }

            accM += A[i];
            if (n - i >= M)
            {
                last[1][i] = Math.max(accM, i+1 < n ? last[1][i+1] : 0);
                accM -= A[i+M-1];
            }
        }

//L might equals to M....
        int ret = 0;
        for(int i = 0; i + 1 < n; ++i)
        {
            ret = Math.max(ret,
                    Math.max(first[0][i]+last[1][i+1],
                            first[1][i]+last[0][i+1]));
        }

        
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.maxSumTwoNoOverlap(new int [] {0,6,5,2,2,5,1,9,4}, 1,2));
    }
}
