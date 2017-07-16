package com.l1337.l447;


import java.util.HashMap;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/number-of-boomerangs/description/
    private double d2(int [] p1, int [] p2) {
        return Math.pow(p1[0]-  p2[0], 2) + Math.pow(p1[1] - p2[1], 2);
    }
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        for (int i = 0; i < points.length; ++i) {
            Map<Double, Integer> map = new HashMap<>();

            for (int j = 0; j < points.length; ++j) {
                if (i == j)
                    continue;
                double d = d2(points[i], points[j]);
                map.put(d, 1 + map.getOrDefault(d, 0));
            }

            for(Integer count: map.values()) {
                ret += count * (count - 1);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
