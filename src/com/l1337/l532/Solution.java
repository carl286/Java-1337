package com.l1337.l532;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int findPairs(int[] nums, int k) {

        //ignore overflow/underflow for now
        int ret = 0;

        //ignore overflow/underflow for k
        if (k < 0)
            return 0;

        Set<Integer> set = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i : nums) {
            if (k == 0) {
                if (set.contains(i))
                    visited.add(i);
                else
                    set.add(i);
            } else if (!set.contains(i)) {
                set.add(i);
                if (set.contains(i-k))
                    ++ret;
                if (set.contains(i + k))
                    ++ret;
            }
        }

        return k == 0 ? visited.size() : ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findPairs(new int []{1,2,3,4,5, 5,4,3,2,1}, 1));
    }
}
