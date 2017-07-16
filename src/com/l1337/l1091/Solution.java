package com.l1337.l1091;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    private int UNREACHABLE = Integer.MAX_VALUE;
    private int LIVE = 0;
    private int get_hash(int i, int j, int N)
    {
        return i * N + j;
    }

    private Pair<Integer, Integer> get_coordinate(int hash, int N)
    {
        return new Pair<Integer, Integer>(hash / N, hash % N);
    }

    private int [][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,+1}, {0,-1}, {+1,+1}, {+1,0},{+1,-1}};

//    https://leetcode.com/problems/shortest-path-in-binary-matrix/discuss/312922/JavaPython-3-concise-BFS-and-DFS-codes-wo-changing-input.
//    https://leetcode.com/problems/shortest-path-in-binary-matrix/discuss/313347/A*-search-in-Python
    public int shortestPathBinaryMatrix(int[][] grid) {
        int N = grid.length;
        if (grid[0][0] == 1 || grid[N-1][N-1] == 1)
            return -1;
        if (N == 1)
            return 1;
        Set<Integer> visited = new HashSet<>();
        List<Integer> queue = new ArrayList<>();
        visited.add(0);
        queue.add(0);

        int count = 1;
        while (!queue.isEmpty())
        {
            ++count;
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < queue.size(); ++i)
            {
                Integer cur = queue.get(i);
                Pair<Integer, Integer> coordinate = get_coordinate(cur, N);
                for (int k = 0; k < directions.length; ++k)
                {
                    int nexti = coordinate.getKey() + directions[k][0];
                    int nextj = coordinate.getValue() + directions[k][1];
                    if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && grid[nexti][nextj] == 0)
                    {
                        if (nexti == N - 1 && nextj == N - 1)
                            return count;
                        int hash = get_hash(nexti, nextj, N);
                        if (!visited.contains(hash))
                        {
                            visited.add(hash);
                            next.add(hash);
                        }
                    }
                }
            }

            queue = next;
        }

        return -1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
