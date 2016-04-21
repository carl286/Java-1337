package com.l1337.l376;

//https://leetcode.com/problems/wiggle-subsequence/


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int wiggleMaxLength(int[] nums) {
        int ret = 0;
        //force it to init
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        for (int k = 0; k < nums.length; ++k) {
            Integer np = 1;
            Integer nn = 1;
            for (int i = 0; i < k; ++i) {
                int diff = nums[i] - nums[k];
                if (diff < 0) {
                    nn = Math.max(nn, 1 + positive.get(i));
                } else if (diff > 0) {
                    np = Math.max(np, 1 + negative.get(i));
                }
            }
            ret = Math.max(ret, Math.max(np, nn));
            positive.add(np);
            negative.add(nn);
        }

        return ret;
    }


    public int wiggleMaxLengthDp2(int[] nums) {
        int p = 1, q = 1;
        for (int i = 1; i < nums.length; ++i) {

            if (nums[i] > nums[i-1]) {
                p = q + 1;
            } else if (nums[i] < nums[i-1]) {
                q = p + 1;
            }
        }

        return Math.min(nums.length, Math.max(p, q));
    }
    public static void main(String [] args) {
        Solution s = new Solution();

        int [] nums = {0,0};
        System.out.println(s.wiggleMaxLength(nums));
    }
}

//

//https://www.hrwhisper.me/leetcode-wiggle-subsequence/
//        http://www.cnblogs.com/grandyang/p/5697621.html

//1. if DP[i] is defined as, longest wiggle(up\down) subseq AT number i, you will have O(n^2) solution
//2. if DP[i] is defined as, longest wiggle(up\down) subseq SO FAR UNTIL number i, you will have O(n) solution

//https://discuss.leetcode.com/topic/52074/concise-10-lines-code-0ms-acepted