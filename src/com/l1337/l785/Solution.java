package com.l1337.l785;


import java.util.*;

public class Solution {

    private boolean dfs(Map<Integer, Boolean> assignment, int i, boolean color, int[][] graph)
    {
        if (assignment.containsKey(i))
        {
            return assignment.get(i) == color;
        }
        assignment.put(i, color);

        for(int k = 0; k < graph[i].length; ++k)
        {
            if (!dfs(assignment, graph[i][k], !color, graph))
                return false;
        }

        return true;
    }
    public boolean isBipartite(int[][] graph) {
        Map<Integer, Boolean> assignment = new HashMap<>();
        int N = graph.length;
        for (int i = 0; i < N; ++i)
        {
            if (!assignment.containsKey(i) && !dfs(assignment, i, true, graph))
                return false;
        }

        return true;
    }

//    https://leetcode.com/problems/is-graph-bipartite/discuss/176266/Clean-and-easy-unionfind-in-JAVA
    public boolean isBipartiteBfs(int[][] graph) {
        Map<Integer, Boolean> assignment = new HashMap<>();
        int N = graph.length;
        for (int i = 0; i < N; ++i)
        {
            if (!assignment.containsKey(i))
            {
                ArrayDeque<Integer> dq = new ArrayDeque<>();
                dq.add(i);
                assignment.put(i, true);
                while (!dq.isEmpty())
                {
                    Integer cur = dq.pollFirst();
                    for (int k = 0; k < graph[cur].length; ++k)
                    {
                        if (assignment.containsKey(graph[cur][k]))
                        {
                            if (assignment.get(graph[cur][k]) == assignment.get(cur))
                                return false;
                        }
                        else
                        {
                            assignment.put(graph[cur][k], !assignment.get(cur));
                            dq.addLast(graph[cur][k]);
                        }
                    }
                }
            }
        }

        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
