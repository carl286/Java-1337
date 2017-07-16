package com.l1337.l200;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    private char X = 'X';
    private char ZERO = '0';
    private char ONE = '1';

    private int [][] directions = new int [][] {{-1, 0}, {+1, 0}, {0, -1}, {0, +1}};
    public int numIslands(char[][] grid) {
        int ret = 0;
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j)
            {
                if (grid[i][j] == ONE)
                {
                    ++ret;
                    Set<Pair<Integer, Integer>> visited = new HashSet<>();
                    LinkedList<Pair<Integer, Integer>> list = new LinkedList<>();
                    Pair<Integer, Integer> p = new Pair<>(i, j);
                    visited.add(p);
                    list.add(p);
//                    grid[i][j] = X;

                    while (!list.isEmpty())
                    {
                        p = list.remove();
                        for (int k = 0; k < directions.length; ++k)
                        {
                            int x = p.getKey();
                            int y = p.getValue();
                            grid[x][y] = X;
                            if (x + directions[k][0] >= 0 && x + directions[k][0] < grid.length && y + directions[k][1] >= 0 && y + directions[k][1] < grid[0].length && grid[x + directions[k][0]][y + directions[k][1]] == ONE)
                            {
                                Pair<Integer, Integer> p2 = new Pair<>(x + directions[k][0], y + directions[k][1]);
                                if (!visited.contains(p2))
                                {
                                    visited.add(p2);
                                    list.add(p2);
                                }

                            }
                        }

                    }
                    //grid[i][j] = X;
                }
            }

        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j)
            {
                if (grid[i][j] == X)
                {
                    grid[i][j] = ONE;
                }
            }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
