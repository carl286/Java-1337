package com.l1337.l1219;


import com.googlePrep.Pack;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    //local, global
    public int [] getMaximumGolddfs(int[][] grid, int x, int y, int m, Set<Integer> visited) {
        visited.add(x * m + y);
        int[] ret = new int [] {grid[x][y], grid[x][y]};

        int [][] directions = new int [][]{{0,-1}, {0, +1}, {-1, 0}, {+1, 0}};
        for (int i = 0; i < directions.length; ++i)
        {
            int new_x = x + directions[i][0];
            int new_y = y + directions[i][1];

            if (new_x >= 0 && new_x < grid.length && new_y >= 0 && new_y < grid[0].length && grid[new_x][new_y] != 0)
            {
                int newHash = new_x * m + new_y;
                if (!visited.contains(newHash))
                {

                    int [] sub = getMaximumGolddfs(grid, new_x, new_y, m, visited);
                    ret[0] = Math.max(ret[0], grid[x][y] + sub[0]);
                    ret[1] = Math.max(ret[0], Math.max(ret[1], sub[1]));

                }
            }
        }

        visited.remove(x * m + y);
        return ret;
    }
    public int getMaximumGold(int[][] grid) {
        int m = grid[0].length;
        Set<Integer> set = new HashSet<>();
        int ret = 0;
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j)
            {
                if (grid[i][j] != 0)
                {
                    int [] tmp = getMaximumGolddfs(grid, i, j, m, set);
                    ret = Math.max(ret, tmp[1]);
                    //System.out.println("i = " + i + "\t j = " + j + "\t: 0:" + tmp[0] + "\t: 1:" + tmp[1]);
                }
            }

        return ret;
    }

    private int getMaximumGoldMemo(int[][] grid, int x, int y, int m, Map<Integer, Integer> map, int mask, int acc, Map<Pair<Integer, Integer>, Integer> memo)
    {
        //UNUSED CODE
        int hash = x * m + y;
        Pair<Integer, Integer> pair= new Pair<>(hash, mask);
        if (memo.containsKey(pair))
        {
            return memo.get(pair);
        }

        int ret = grid[x][y];
        mask |= 1 << map.get(hash);

        memo.put(pair, ret);
        return memo.get(pair);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.getMaximumGold(new int [][]{{0, 0, 19, 5,8}, {11,20,14,1,0}, {0,0,1,1,1}, {0,2,0,2,0}}));
    }
}
