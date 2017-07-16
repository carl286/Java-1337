package com.l1337.l573;


public class Solution {

    /*
    There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.

Example 1:

Input:
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:
Note:

All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.
     */

//    http://www.cnblogs.com/grandyang/p/6919923.html
    private int dis(int []a, int []b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        //at least one nut is guaranteed
        //assume no overlap..


        //if concrete example not working, why not try to make it abstract....
        int min_delta = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nuts.length; ++i) {
            int dt = dis(nuts[i], tree);
            sum += (dt << 1);
            min_delta = Math.min(min_delta, dis(squirrel, nuts[i]) - dt);
        }

        return sum + min_delta;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
