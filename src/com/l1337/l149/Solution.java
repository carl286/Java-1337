package com.l1337.l149;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    //case first....
//    https://leetcode.com/submissions/detail/62840291/
    public int maxPoints(int[][] points) {
        int ret = 0;

        //below implementions are wrong
        int horizontal = 0;
        Map<Double, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; ++i)
        {
            if (points[i][0] == 0)
            {
                ++horizontal;
                if (horizontal > ret)
                    ret = horizontal;
            }
            else
            {
                double k = points[i][1] / (double) points[i][0];
                map.put(k, 1 + map.getOrDefault(k, 0));
                ret = Math.max(ret, map.get(k));
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");

        int[][] points = new int [][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        System.out.println(s.maxPoints(points));
    }
}
