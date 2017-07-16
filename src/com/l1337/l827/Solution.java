package com.l1337.l827;

import javafx.util.Pair;
import java.util.*;

public class Solution {

    private boolean isValid(int i, int j, int [][] grid)
    {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    private int populateIsland(int i, int j, int [][] grid, int coverValue, Set<Pair<Integer, Integer>> recheck)
    {
        int ret = 1;
        grid[i][j] = coverValue;
        Deque<Pair<Integer, Integer>> deque = new ArrayDeque<>();
        deque.add(new Pair<>(i,j));
        while (!deque.isEmpty())
        {
            Pair<Integer, Integer> current = deque.pollFirst();
            for(int k = 0; k < directions.length; ++k)
            {
                int next_i = current.getKey() + directions[k][0];
                int next_j = current.getValue() + directions[k][1];
                if (isValid(next_i, next_j, grid))
                {
                    if (grid[next_i][next_j] == 1)
                    {
                        grid[next_i][next_j] = coverValue;
                        ++ret;
                        deque.addLast(new Pair<>(next_i, next_j));
                    }
                    else if (grid[next_i][next_j] == 0)
                    {
                        recheck.add(new Pair<>(next_i, next_j));
                    }
                }
            }
        }

        return ret;
    }
    private int [][] directions = new int [][] {{-1,0}, {+1,0}, {0,-1}, {0,+1}};

    public int largestIsland(int[][] grid) {
        int maxIsland = 0;
        int islandIndex = 2;
        //index -> islandSize
        Map<Integer, Integer> map = new HashMap<>();
        Set<Pair<Integer, Integer>> recheck = new HashSet<>();
        for(int i = 0; i < grid.length; ++i)
        {
            for(int j = 0; j < grid[0].length; ++j)
            {
                if (grid[i][j] == 1)
                {
                    int isLandSize = populateIsland(i,j,grid,islandIndex, recheck);
                    map.put(islandIndex, isLandSize);
                    ++islandIndex;
                    if (isLandSize > maxIsland)
                        maxIsland = isLandSize;
                }
            }
        }

        int ret = 0;
        Iterator<Pair<Integer, Integer>> iterator = recheck.iterator();
        while (iterator.hasNext())
        {
            Pair<Integer, Integer> current = iterator.next();
            int i = current.getKey();
            int j = current.getValue();
            int local = 1;
            Set<Integer> uniqueIslands = new HashSet<>();
            for(int k = 0; k < directions.length; ++k)
            {
                int next_i = i + directions[k][0];
                int next_j = j + directions[k][1];
                if (isValid(next_i, next_j, grid) && grid[next_i][next_j] != 0)
                    uniqueIslands.add(grid[next_i][next_j]);
            }

            Iterator<Integer> unique = uniqueIslands.iterator();
            while(unique.hasNext())
            {
                Integer currentIslandIndex = unique.next();
                local += map.get(currentIslandIndex);
            }

            ret = Math.max(local, ret);
        }

        if (recheck.isEmpty() && grid.length >= 1)
            ret = Math.max(ret, 1);

        ret = Math.max(maxIsland, ret);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        // int [][] grid = new int [][] {{0,0},{0,0}};
        int [][] grid = new int [][] {{0,0,0,0,0,0,0},{0,1,1,1,1,0,0},{0,1,0,0,1,0,0},{1,0,1,0,1,0,0},{0,1,0,0,1,0,0},{0,1,0,0,1,0,0},{0,1,1,1,1,0,0}};
        System.out.println(s.largestIsland(grid));
    }
}
