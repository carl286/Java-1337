package com.l1337.l1438;


import java.util.ArrayDeque;
import java.util.TreeMap;

public class Solution {

//    https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609771/JavaC%2B%2BPython-Deques-O(N)
//    https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609743/Java-Detailed-Explanation-Sliding-Window-Deque-O(N)
    public int longestSubarray(int[] nums, int limit) {
        if (nums.length <= 1)
            return nums.length;
        int ret = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int start = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            map.put(nums[i], 1 + map.getOrDefault(nums[i], 0));
            while (i > start)
            {
                int low = map.firstKey();
                int high = map.lastKey();
                if (high - low <= limit)
                {
                    ret = Math.max(i - start + 1, ret);
                    break;
                }
                else
                {
                    if (map.get(nums[start]) == 1)
                        map.remove(nums[start]);
                    else
                        map.put(nums[start], map.get(nums[start]) - 1);
                    ++start;
                }
            }
        }


        return ret;
    }

    public int longestSubarrayOn(int[] nums, int limit) {
        if (nums.length <= 1)
            return nums.length;
        int ret = 1;
        ArrayDeque<Integer> minq = new ArrayDeque<>();
        ArrayDeque<Integer> maxq = new ArrayDeque<>();

        for (int start = 0, i = 0; i < nums.length; ++i)
        {
            //new value
            while (!minq.isEmpty() && minq.peekFirst() > nums[i])
            {
                minq.removeFirst();
            }
            minq.addFirst(nums[i]);
            while (!maxq.isEmpty() && maxq.peekFirst() < nums[i])
            {
                maxq.removeFirst();
            }
            maxq.addFirst(nums[i]);
            //minq.last, small, maxq.last max
            while (maxq.peekLast() - minq.peekLast() > limit)
            {
                if (nums[start] == maxq.peekLast())
                    maxq.removeLast();
                if (nums[start] == minq.peekLast())
                    minq.removeLast();
                ++start;
            }
            ret = Math.max(ret, i - start + 1);
        }


        return ret;
    }
        public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
