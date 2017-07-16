package com.l1337.l463;


public class Solution {

//    https://leetcode.com/problems/island-perimeter/discuss/94992/Java-9-line-solution-add-4-for-each-land-and-remove-2-for-each-internal-edge
//    https://leetcode.com/problems/island-perimeter/discuss/95001/clear-and-easy-java-solution
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    //check up
                    if (i == 0 || grid[i-1][j] == 0)
                        ++sum;
                    //check down
                    if (i + 1 == grid.length || grid[i+1][j] == 0)
                        ++sum;
                    //check left;
                    if (j == 0 || grid[i][j-1] == 0)
                        ++sum;
                    //check right
                    if (j + 1 == grid[0].length || grid[i][j+1] == 0)
                        ++sum;
                    System.out.println("\t" + i + "\t" + j + "\t" + sum );
                }
            }
        }

        return sum;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int grid[][] = new int[][] {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(s.islandPerimeter(grid));
    }
}
