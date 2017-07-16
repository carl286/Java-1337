package com.l1337.l930;


import com.googlePrep.Pack;

public class Solution {

//    https://leetcode.com/problems/binary-subarrays-with-sum/discuss/186683/C%2B%2BJavaPython-Sliding-Window-O(1)-Space
    public int numSubarraysWithSum(int[] A, int S) {
        if (A.length < S)
            return 0;
        return numSubarraysWithAtMostSum(A, S) - numSubarraysWithAtMostSum(A, S-1);
    }

    public int numSubarraysWithAtMostSum(int[] A, int S) {
        if (S < 0)
            return 0;
        //S is at least 0 here
        int ret = 0;
        int acc = 0;
        for (int start = 0, cur = 0; cur < A.length; ++cur)
        {
            acc += A[cur];
            while (acc > S)
            {
                acc -= A[start++];
            }
            ret += ((cur - start + 1));

        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.numSubarraysWithSum(new int [] {1,0,1,0,1}, 2));
    }
}
