package com.l1337.l939;


import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int minAreaRect(int[][] points) {
        int ret = Integer.MAX_VALUE;
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        for(int i = 0; i < points.length; ++i)
        {
            set.add(new Pair<>(points[i][0], points[i][1]));
        }
        for(int i = 0; i < points.length; ++i)
        {
            for(int j = i + 1; j < points.length; ++j)
            {
                Pair<Integer, Integer> c = new Pair<>(points[i][0], points[j][1]);
                Pair<Integer, Integer> d = new Pair<>(points[j][0], points[i][1]);
                if (set.contains(c) && set.contains(d))
                {
                    int area = Math.abs((points[i][0] - points[j][0]) * (points[i][1] - points[j][1]));
                    if (area > 0 && ret > area)
                        ret = area;
                }

            }
        }

        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
