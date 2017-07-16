package com.l1337.l414;


import java.util.TreeSet;

public class Solution {

    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                continue;
            } else if (set.size() < 3) {
                set.add(i);
            } else {
                int first = set.first();
                if (i > first) {
                    set.pollFirst();
                    set.add(i);
                }
            }
        }

        if (set.size() < 3)
            return set.last();
        else
            return set.first();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int a [] = {2, 2, 3, 1};
        System.out.println(s.thirdMax(a));
    }
}
