package com.l1337.l575;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int i : candies)
            set.add(i);

        return Math.min(set.size(), candies.length / 2);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
