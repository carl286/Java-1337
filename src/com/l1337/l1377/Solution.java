package com.l1337.l1377;


import java.util.*;

public class Solution {

//    https://leetcode.com/problems/frog-position-after-t-seconds/discuss/532505/Java-Straightforward-BFS-Clean-code-O(N)
    public double frogPosition(int n, int[][] edges, int t, int target) {
        //we don't need a fan out here since this is a tree, the fan out would always be #connected - 1 except the root
        //the description of this problem is shit....

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i)
        {
            int a = edges[i][0], b = edges[i][1];
            graph.putIfAbsent(a, new HashSet<>());
            graph.putIfAbsent(b, new HashSet<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        Set<Integer> expanded = new HashSet<>();
        expanded.add(1);
        Map<Integer, Integer> depths = new HashMap<>();
        depths.put(1, 0);
        Map<Integer, Double> probablity = new HashMap<>();
        probablity.put(1, 1.0);

        int steps = 0;
        while (steps < t && !list.isEmpty())
        {
            ++steps;
            ArrayList<Integer> next = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i)
            {
                int parent = list.get(i);
                if (graph.containsKey(parent))
                {
                    Iterator<Integer> it = graph.get(parent).iterator();
                    while (it.hasNext())
                    {
                        int child = it.next();
                        if (!expanded.contains(child))
                        {
                            probablity.put(child, probablity.get(parent) / (parent == 1 ? graph.get(parent).size() : graph.get(parent).size() - 1));
                            depths.put(child, steps);
                            expanded.add(child);
                            next.add(child);
                        }
                    }
                }

            }
            list = next;
        }

        if (depths.containsKey(target))
        {
            if (depths.get(target) == t)
                return probablity.get(target);
            else if (target == 1)
            {
                if(graph.getOrDefault(1, new HashSet<>()).size() == 0)
                    return probablity.get(target);
                else
                    return 0.0;
            }
            else if (graph.get(target).size() == 1)
                return probablity.get(target);
            else
                return 0.0;
        }
        else
            return 0.0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        int n = 7;
//        int [][] edges = new int [][]{{1,2}, {1,3}, {1,7}, {2,4}, {2,6}, {3,5}};
//        int t = 2;
//        int target = 4;
//        System.out.println(s.frogPosition(n, edges, t, target));
//        int n = 8;
//        int [][] edges = new int [][]{{2,1}, {3,2}, {4,1}, {5,1}, {6,4}, {7,1}, {8,7}};
//        int t = 7;
//        int target = 7;
//        System.out.println(s.frogPosition(n, edges, t, target));
//        int n = 1;
//        int [][] edges = new int [0][2];
//        int t = 1;
//        int target = 1;
//        System.out.println(s.frogPosition(n, edges, t, target));
        int n = 9;
        int [][] edges = new int [][]{{2,1}, {3,2}, {4,3}, {5,3}, {6,5}, {7,3}, {8,4}, {9,5}};
        int t = 9;
        int target = 1;
        System.out.println(s.frogPosition(n, edges, t, target));
    }
}
