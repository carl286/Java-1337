package com.l1337.l528;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Solution {

    private TreeMap<Integer, Integer> map = new TreeMap<>();
    private int total;
    private Random randomGenerator;

    public Solution(int[] w) {
        randomGenerator = new Random();
        total = 0;
        for (int i = 0; i < w.length; ++i) {
            total += w[i];
            map.put(total, i);
        }
    }

//    https://leetcode.com/problems/random-pick-with-weight/discuss/154969/Java-solution-with-explaination
    public int pickIndex() {
        int x = Integer.MAX_VALUE;
        return map.ceilingEntry(randomGenerator.nextInt(total) + 1).getValue();
    }
    public static void main(String [] args) {
        Solution s = new Solution(new int []{1,100});
        for (int i = 0; i < 10; ++i)
        System.out.println(s.pickIndex());
    }
}
