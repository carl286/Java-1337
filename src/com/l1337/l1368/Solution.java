package com.l1337.l1368;


import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    private int [][] directions = new int[][]{{}, {0, +1}, {0, -1}, {+1, 0}, {-1, 0}};
    private boolean isValid(int i, int j, int [][] grid)
    {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[i].length;
    }

    public void minCostHelper(int i, int j, int[][] grid, Set<Pair<Integer, Integer>> visited, int [] mins, int acc) {
        if (i == grid.length - 1 && j == grid[i].length - 1)
        {
            mins[0] = Math.min(acc, mins[0]);
        }

        for(int k = 1; k < directions.length; ++k)
        {
            int next_i = i + directions[k][0];
            int next_j = j + directions[k][1];
            Pair<Integer, Integer> nextPair = new Pair<Integer, Integer>(next_i, next_j);
            if(isValid(next_i, next_j, grid) && !visited.contains(nextPair))
            {
                visited.add(nextPair);
                minCostHelper(next_i, next_j, grid, visited, mins, acc + (k == grid[i][j] ? 0 : 1));
                visited.remove(nextPair);
            }

        }
    }

    public int minCostHelperWithReturn(int i, int j, int[][] grid, Set<Pair<Integer, Integer>> visited, int acc) {
        if (i == grid.length - 1 && j == grid[i].length - 1)
        {
            return acc;
        }

        int ret = Integer.MAX_VALUE;
        for(int k = 1; k < directions.length; ++k)
        {
            int next_i = i + directions[k][0];
            int next_j = j + directions[k][1];
            Pair<Integer, Integer> nextPair = new Pair<Integer, Integer>(next_i, next_j);
            if(isValid(next_i, next_j, grid) && !visited.contains(nextPair))
            {
                visited.add(nextPair);
                ret = Math.min(ret, minCostHelperWithReturn(next_i, next_j, grid, visited, acc + (k == grid[i][j] ? 0 : 1)));
                visited.remove(nextPair);
            }

        }
        return ret;
    }

//    https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/discuss/524820/Java-2-different-solutions-Clean-code
    public int minCost(int[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0)
            return 0;

        int [] mins = new int [1];
        mins[0] = grid.length + grid[0].length - 2;
        Set<Pair<Integer, Integer>> visted = new HashSet<>();
        visted.add(new Pair<>(0,0));
        minCostHelper(0, 0, grid, visted, mins, 0);
        return mins[0];
    }
    public static void main(String [] args) {
        Solution s = new Solution();

        System.out.println("Hello World");
    }
}
