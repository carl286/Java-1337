package com.l1337.l1293;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    private int [][] directions = new int [][] {{-1,0}, {+1,0}, {0,-1}, {0,+1}};
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1)
            return 0;//edge cases, pending discussion.
        //from remaining removing
        Map<Integer, Set<Pair<Integer, Integer>>> visited = new HashMap<>();
        for(int i = 0; i <= k; ++i)
            visited.put(i, new HashSet<>());
        Deque<int []> dq = new ArrayDeque<>();
        Pair<Integer, Integer> p0 = new Pair<Integer, Integer>(0,0);
        dq.addLast(new int [] {0,0,k});
//        visited.putIfAbsent(k, new HashSet<>());
        visited.get(k).add(p0);

        int steps = 0;
        while (!dq.isEmpty())
        {
            ++steps;
            int size = dq.size();
            while (size-- > 0)
            {
                int [] current = dq.pollFirst();
                for(int i = 0; i < directions.length; ++i)
                {
                    int next_i = current[0] + directions[i][0];
                    int next_j = current[1] + directions[i][1];
                    if (next_i >= 0 && next_i < m && next_j >= 0 && next_j < n)
                    {
                        //check if terminal first
                        if (next_i == m - 1 && next_j == n - 1)
                        {
                            if (grid[m-1][n-1] == 0 || current[2] >= 1)
                                return steps;
                        }
                        else
                        {
                            Pair<Integer, Integer> next = new Pair<>(next_i, next_j);
                            //try to expand the node
                            if (grid[next_i][next_j] == 0)
                            {
                                if (!visited.get(current[2]).contains(next))
                                {
                                    // visited.putIfAbsent(current[2], new HashSet<>());
                                    visited.get(current[2]).add(next);
                                    dq.addLast(new int [] {next_i, next_j, current[2]});
                                }
                            }
                            else if (current[2] >= 1)
                            {
                                if (!visited.get(current[2]-1).contains(next))
                                {
                                    //visited.putIfAbsent(current[2]-1, new HashSet<>());
                                    visited.get(current[2]-1).add(next);
                                    dq.addLast(new int [] {next_i, next_j, current[2]-1});
                                }
                            }
                        }
                    }
                }

            }

        }


        return -1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
