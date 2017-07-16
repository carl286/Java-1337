package com.l1337.l963;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    private int distance(int [] a, int [] b)
    {
        return (a[0]-b[0]) * (a[0]-b[0]) + (a[1]-b[1]) *(a[1] - b[1]);
    }
    private boolean isPerpendicularTriangle(int [] a, int [] b, int []c)
    {
        return distance(a,b) == distance(a,c) + distance(b,c);
    }
    private Pair<Integer, Integer> getPointD(int [] a, int [] b, int []c)
    {
        return new Pair<> (a[0] + b[0] - c[0], a[1] + b[1] - c[1]);
    }
    private int getRectSize(int [] a, int [] b, int []c)
    {
        return Math.abs(b[0]*c[1] +c[0]*a[1] +a[0]*b[1] - b[0]*a[1] - c[0]*b[1]- a[0]*c[1]);
    }

//    https://leetcode.com/problems/minimum-area-rectangle-ii/discuss/210786/C%2B%2B-with-picture-find-diagonals-O(n-*-n)
    public double minAreaFreeRect(int[][] points) {

        //do you need sort here???
//        Arrays.sort(points, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] a, int[] b) {
//                if (a[0] != b[0])
//                    return a[0] - b[0];
//                return a[1] - b[1];
//            }
//
//        });

        //assume all points are unique
        Set<Pair<Integer, Integer>> set = new HashSet<>();

        for(int i = 0; i < points.length; ++i)
        {
            set.add(new Pair<>(points[i][0],points[i][1]));
        }

        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; ++i)
        {
            for(int j = i + 1; j < points.length; ++j)
            {
                for (int k = j + 1; k < points.length; ++k)
                {
                    if (isPerpendicularTriangle(points[i], points[j], points[k]))
                    {
                        if (set.contains(getPointD(points[i], points[j], points[k])))
                        {
                            int localSize =getRectSize(points[i], points[j], points[k]);
                            if (localSize != 0 && ret > localSize)
                            {
                                ret = localSize;
                            }
                        }

                    }
                }
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        // int[][] points = new int [][] {{1,2},{2,1},{1,0},{0,1}};
        // int[][] points = new int [][] {{0,3}, {1,2}, {3,1}, {1,3}, {2,1}};
        int[][] points = new int [][] {{3,1}, {1,1}, {0,1}, {2,1}, {3,3}, {3,2}, {0,2}, {2,3}};
        System.out.println(s.minAreaFreeRect(points));
    }
}
