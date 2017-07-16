package com.l1337.l750;


public class Solution {
    /*
    Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.
Example 1:
Input: grid =
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
Example 2:
Input: grid =
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
Example 3:
Input: grid =
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.
Note:
The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.
     */

    //O(m^2*n^2)
    public int countCornerRectanglesMay4_21(int[][] grid) {
        if (grid.length <= 1 || grid[0].length <= 1)
            return 0;
        int m = grid.length, n = grid[0].length;
        int ret = 0;
        for(int i = 0; i < m; ++i)
            for(int j = 0; j < n; ++j)
            {
                for(int l = 1; i + l < m; ++l)
                {
                    for(int k = 1; j + k < n; ++k)
                    {
                        if (grid[i][j] == 1 && grid[i][j+k] == 1 && grid[i+l][j] == 1 && grid[i+l][j+k] == 1)
                        {
                            ++ret;
                        }
                    }
                }
            }

        return ret;
    }

    //O(m^2*n)
    public int countCornerRectanglesMay4_21_n3(int[][] grid) {
        if (grid.length <= 1 || grid[0].length <= 1)
            return 0;
        int m = grid.length, n = grid[0].length;
        int ret = 0;
        //pick up any two rows
        for(int i = 0; i < m; ++i)
        {
            for(int j = i + 1; j < m; ++j)
            {
                int counts = 0;
                for(int k = 0; k < n; ++k)
                {
                    if (grid[i][k] == 1 && grid[j][k] == 1)
                        ++counts;
                }
                ret += counts * (counts-1)/2;
            }
        }

        return ret;
    }















    /*
    朴素解法是枚举左上角和右下角坐标，时间复杂度 O(m^2 * n^2)，会导致TLE
     */
//    http://www.cnblogs.com/grandyang/p/8433813.html
    public int countCornerRectangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int x = 0; x < m; x++) {
            for (int y = x + 1; y < m; y++) {
                int cnt = 0;
                for (int z = 0; z < n; z++) {
                    if (grid[x][z] == 1 && grid[y][z] == 1) {
                        cnt++;
                    }
                }
                ans += cnt * (cnt - 1) / 2;
            }
        }
        return ans;
    }






























    public static void main(String [] args) {
        Solution s = new Solution();
//        int [][] grid = new int [][] {
//                {1, 0, 0, 1, 0},
//                {0, 0, 1, 0, 1},
//                {0, 0, 0, 1, 0},
//                {1, 0, 1, 0, 1}
//        };
        int [][] grid = new int [][] {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println(s.countCornerRectanglesMay4_21_n3(grid));
    }
}
