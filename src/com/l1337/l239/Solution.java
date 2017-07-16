package com.l1337.l239;


import java.util.ArrayDeque;
import java.util.Stack;

public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        //assume k > 0
        if (nums.length < k)
            return new int [0];

        int [] ret = new int [nums.length - k + 1];
        // think about the definition of this dq....
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < k; ++i)
        {
            while (!dq.isEmpty() && dq.peekFirst() < nums[i])
            {
                dq.removeFirst();
            }
            dq.addFirst(nums[i]);
        }
        int index = 0;
        ret[index++] = dq.peekLast();


        for (int i = k; i < nums.length; ++i)
        {
            //how to remove i - k ???
            if (nums[i - k] == dq.peekLast())
                dq.removeLast();
            while (!dq.isEmpty() && dq.peekFirst() < nums[i])
            {
                dq.removeFirst();
            }
            dq.addFirst(nums[i]);
            ret[index++] = dq.peekLast();
        }


        return ret;
    }

    //an interesting idea
//    https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
