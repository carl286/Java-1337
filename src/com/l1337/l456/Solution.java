package com.l1337.l456;


import java.util.Stack;

public class Solution {

//    https://leetcode.com/problems/132-pattern/description/

//    https://leetcode.com/problems/132-pattern/discuss/94071/Single-pass-C++-O(n)-space-and-time-solution-(8-lines)-with-detailed-explanation.
//    https://leetcode.com/problems/132-pattern/discuss/94077/Java-O(n)-solution-using-stack-in-detail-explanation
    public boolean find132pattern(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int maxIndex = i;
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[maxIndex] > nums[j] && nums[j] > nums[i]) {
                    return true;
                }
                if (nums[j] > nums[maxIndex])
                    maxIndex = j;
            }
        }
        return false;
    }

    public boolean find132pattern2(int[] nums) {
        int s3 = Integer.MIN_VALUE;
        Stack<Integer> st = new Stack<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] < s3)
                return true;
            while (!st.isEmpty() && nums[i] > st.peek()) {
                s3 = st.pop();
            }
            st.push(nums[i]);
        }

        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.find132pattern(new int [] {1,0,1,-4,-3}));
    }
}
