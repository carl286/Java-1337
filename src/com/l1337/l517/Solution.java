package com.l1337.l517;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

//   https://leetcode.com/problems/super-washing-machines/discuss/99185/Super-Short-and-Easy-Java-O(n)-Excel
//    https://leetcode.com/problems/super-washing-machines/discuss/99177/Very-intuitive-O(n)-solutionhttps://leetcode.com/problems/super-washing-machines/discuss/99181/c-16ms-on-solution-with-trivial-proof
//    https://leetcode.com/problems/super-washing-machines/discuss/99181/c-16ms-on-solution-with-trivial-proof
    public int findMinMoves(int[] machines) {
        int total = 0;
        for (int i : machines)
            total += i;

        int len = machines.length;
        int [] sum = new int [machines.length + 1];
        for (int i = 0; i < len; ++i)
            sum[i + 1] = sum[i] + machines[i];

        if (sum[len] % len != 0) return -1;

        int avg = sum[len] / len;
        int res = 0;
        for (int i = 0; i < len; ++i)
        {
            int l = i * avg - sum[i];
            int r = (len - i - 1) * avg - (sum[len] - sum[i] - machines[i]);

            if (l > 0 && r > 0)
                res = Math.max(res, Math.abs(l) + Math.abs(r));
            else
                res = Math.max(res, Math.max(Math.abs(l), Math.abs(r)));
        }
        return res;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
