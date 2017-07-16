package com.l1337.l1102;


public class Solution {

    /*
    https://junhaow.com/lc/problems/heap/1102_path-with-maximum-minimum-value.html
    Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0, 0] and ending at [R-1, C-1].

The score of a path is the minimum value in that path. For example, the value of the path 8 → 4 → 5 → 9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).

Note:

1 <= R, C <= 100
0 <= A[i][j] <= 10^9

Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation:
The path with the maximum score is highlighted in yellow.

Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2

Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3
     */
    private int [][] directions = new int [][] {{0,-1}, {0,+1}, {-1,0}, {+1, 0}};
    //super bad description of this problem...
    public int maximumMinimumPath(int[][] A) {
        return 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
