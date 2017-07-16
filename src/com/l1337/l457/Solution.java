package com.l1337.l457;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/circular-array-loop/description/
//    http://www.cnblogs.com/grandyang/p/7658128.html
//    http://bookshadow.com/weblog/2016/11/09/leetcode-circular-array-loop/
    public boolean circularArrayLoop(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(i))
                continue;
            int cur = i;
            while (true) {
                set.add(cur);
                int next = (cur + nums[i]) % nums.length;
                if (next < 0)
                    next += nums.length;
                if (next == cur || nums[next] * nums[cur] < 0)
                    break;
                if (set.contains(next))
                    return true;

                cur = next;
            }
        }

        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
