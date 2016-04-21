package com.l1337.l361;

/*
We have a 2D grid. Each cell is either a wall, an enemy or empty.

For example (0-empty, X-enemy, Y-wall):

0 X 0 0
X 0 Y X
0 X 0 0
You have one bomb and you want to kill as many as possible enemies with it.
The bomb will kill all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.

Given such a grid, return the maximum enemies you can kill with one bomb.
Note that you can only put the bomb at empty cell.

In the example, if you put a bomb at (1,1) you will kill 3 enemies which is the best you can get. You can not kill the guy behind the wall at (1,3).
 */
public class Solution {

    private final static char ENEMY = 'X';
    private final static char WALL = 'Y';
    private final static char EMPTY = '0';


    //Very sweet answer...
//    https://leetcode.com/discuss/109116/short-o-mn-solution
//    https://discuss.leetcode.com/topic/10/bomb-enemy/5   <--Basically share the same Key points as previous one. But previous one is definitely better...
    int maxKilledEnemies(char [][] grid) {

        //Below method time complexity is too high... You can make it better....
        //assume the gird is not 0..
        //do it like graph
        int [][] cnt = new int [grid.length][];
        for (int i = 0; i < cnt.length; ++i)
            cnt[i] = new int [grid[0].length];

        int ret = 0;
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == ENEMY) {
                    //extend four directions
                    //up
                    for (int k = i - 1; k >=0 && grid[k][j] != WALL; --k)
                        if (grid[k][j] == EMPTY)
                            ++cnt[k][j];
                    //down
                    for (int k = i + 1; k < grid.length && grid[k][j] != WALL; ++k)
                        if (grid[k][j] == EMPTY)
                            ++cnt[k][j];
                    //left
                    for (int k = j - 1; k >= 0 && grid[i][k] != WALL;--k)
                        if (grid[i][k] == EMPTY)
                            ++cnt[i][k];
                    //right
                    for (int k = j + 1; k < grid[i].length && grid[i][k] != WALL;++k)
                        if (grid[i][k] == EMPTY)
                            ++cnt[i][k];
                }
            }
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j) {
                ret = Math.max(ret, cnt[i][j]);
            }
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();

        char grid [][] = {
            {'0','X','0','0'},
            {'X','0','Y','X'},
            {'0','X','0','0'},
        };
        System.out.println(s.maxKilledEnemies(grid));
    }
}
