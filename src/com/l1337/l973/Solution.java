package com.l1337.l973;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
//    https://leetcode.com/problems/k-closest-points-to-origin/discuss/217966/C%2B%2B-3-lines-nth_element-O(n)
    public int[][] kClosest(int[][] points, int k) {
        int [][] tmp = new int[points.length][2]; // val, index
        for(int i = 0; i < points.length; ++i)
        {
            tmp[i][0] = points[i][0]*points[i][0] + points[i][1]*points[i][1];
            tmp[i][1] = i;
        }
        Arrays.sort(tmp, (a,b)->(a[0]-b[0]));
        int [][] ret = new int [k][2];
        for(int i = 0; i < ret.length; ++i)
        {
            ret[i][0] = points[tmp[i][1]][0];
            ret[i][1] = points[tmp[i][1]][1];
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
