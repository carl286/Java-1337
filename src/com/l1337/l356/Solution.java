package com.l1337.l356;


/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Hint:

Find the smallest and largest x-value for all points.
If there is a line then it should be at y = (minX + maxX) / 2.
For each point, make sure that it has a reflected point in the opposite side.

 */

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    //so no points are duplicate...points are not empty...
    public boolean isReflected(int[][] points) {

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        //previously I was thinking sort, find left and right, got the middle
        //But the hints simply suggests find them by one loop.
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; ++i) {
            if (map.containsKey(points[i][0])) {
                map.get(points[i][0]).add(points[i][1]);
            } else {
                HashSet<Integer> tmp = new HashSet<>();
                tmp.add(points[i][1]);
                map.put(points[i][0], tmp);
            }
            minX = Math.min(minX, points[i][0]);
            maxX = Math.max(maxX, points[i][1]);
        }

        //should we use float for middle????
        long middle = (maxX + minX);
        for (int i = 0; i < points.length; ++i) {
            int x = points[i][0];
            int x2 = (int) (middle - x);
            //it's OK to be on the middle line.....
            //Becarefull.....

            //we should remove them from the set to impore performacens.
            //if you do remove them, then you need check existence first.....
            if (!(x == x2 || (map.containsKey(x2) && map.get(x2).contains(points[i][1]))))
                return false;
        }
        return true;
    }


    //Below has overflow issue, But how to set up the map is good.
//    public boolean isReflected(int[][] points) {
//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//        HashSet<String> set = new HashSet<>();
//        for(int[] p:points){
//            max = Math.max(max,p[0]);
//            min = Math.min(min,p[0]);
//            String str = p[0] + "a" + p[1];
//            set.add(str);
//        }
//        int sum = max+min;
//        for(int[] p:points){
//            //int[] arr = {sum-p[0],p[1]};
//            String str = (sum-p[0]) + "a" + p[1];
//            if( !set.contains(str))
//                return false;
//
//        }
//        return true;
//    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [][] points = {{1,1},{-1,1}};
        System.out.println(s.isReflected(points));
    }
}
