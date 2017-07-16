package com.l1337.l1425;


import java.util.ArrayDeque;

public class Solution {

    public int constrainedSubsetSum(int[] nums, int k) {
        if (nums.length <= 0)
            return -1;
        int [] tmp = new int[nums.length];
        int ret = nums[0];

        for (int i = 0; i < nums.length; ++i)
        {
            int localMax = nums[i];
            for (int j = 1; j <= k && i >= j; ++j)
            {
                localMax = Math.max(localMax, (tmp[i - j]) + nums[i]);
            }
            tmp[i] = localMax;
            ret = Math.max(tmp[i], ret);
        }

        return ret;
    }

    /*
    DP: the max sum of such subsequence ending with A[i] is S[i] = max(S[i-k:i]) + A[i]. Answer is max(S).
We use deque to maintain a decreasing queue with max(S[i-k:i]) at the head of the queue.
     */
    public int constrainedSubsetSumOn(int[] nums, int k) {
        if (nums.length <= 0)
            return -1;

        int ret = nums[0];
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < nums.length; ++i)
        {
            int attempt = Math.max((dq.isEmpty() ? 0 : dq.peekFirst()) + nums[i], nums[i]);
            while (!dq.isEmpty() && dq.peekLast() < attempt)
                dq.removeLast();
            dq.addLast(attempt);
            if (i >= k && !dq.isEmpty() && nums[i-k] == dq.peekFirst())
            {
                dq.removeFirst();
                //remove values nums[i-k]
            }
            nums[i] = attempt;
            ret = Math.max(attempt, ret);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
