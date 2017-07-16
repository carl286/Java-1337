package com.l1337.l1443;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    //this problem is very very confusing in how the edges been given...
    /*
    The graph representation is horrible
    4
[[0,2],[0,3],[1,2]]
[false,true,false,false]
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {

        int [] childrenCounts = new int [n];
        Map<Integer, Integer> childToParent = new HashMap<>();

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i)
        {
            if (graph.get(edges[i][0]) == null)
                graph.put(edges[i][0], new ArrayList<>());
            if (graph.get(edges[i][1]) == null)
                graph.put(edges[i][1], new ArrayList<>());
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        //travel from 0
        LinkedList<Integer> toVisit = new LinkedList<>();
        toVisit.add(0);

        Set<Integer> visited = new HashSet<>();
        while (!toVisit.isEmpty())
        {
            Integer cur = toVisit.poll();
            visited.add(cur);
            if (graph.get(cur) != null)
            {
                for (int i = 0; i < graph.get(cur).size(); ++i)
                {
                    if (!visited.contains(graph.get(cur).get(i)))
                    {
                        childrenCounts[cur]++;
                        childToParent.put(graph.get(cur).get(i), cur);
                        toVisit.add(graph.get(cur).get(i));
                    }

                }

            }
        }

        LinkedList<Integer> candidates = new LinkedList<>();
        for (int i = 0; i < childrenCounts.length; ++i)
            if (childrenCounts[i] == 0)
                candidates.add(i);

        boolean [] hasApples = new boolean[n];
        for (int i = 0; i < hasApple.size(); ++i)
            if (hasApple.get(i))
                hasApples[i] = true;

        while (!candidates.isEmpty())
        {
            Integer cur = candidates.poll();
            //update parents
            if (childToParent.get(cur) != null)
            {
                int parent = childToParent.get(cur);
                hasApples[parent] |= hasApples[cur];
                --childrenCounts[parent];
                if (childrenCounts[parent] == 0)
                    candidates.add(parent);
            }
        }

        int c = 0;
        for (int i = 0; i < hasApples.length; ++i)
            if (hasApples[i])
                ++c;

        return c == 0 ? 0 : 2 * (c - 1);
    }


    public int minTimev2(int n, int[][] edges, List<Boolean> hasApple) {
        int [] childrenCounts = new int [n];
        Map<Integer, Integer> childToParent = new HashMap<>();

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i)
        {
            if (graph.get(edges[i][0]) == null)
                graph.put(edges[i][0], new ArrayList<>());
            if (graph.get(edges[i][1]) == null)
                graph.put(edges[i][1], new ArrayList<>());
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        //travel from 0
        LinkedList<Integer> toVisit = new LinkedList<>();
        toVisit.add(0);

        Set<Integer> visited = new HashSet<>();
        while (!toVisit.isEmpty())
        {
            Integer cur = toVisit.poll();
            visited.add(cur);
            if (graph.get(cur) != null)
            {
                for (int i = 0; i < graph.get(cur).size(); ++i)
                {
                    if (!visited.contains(graph.get(cur).get(i)))
                    {
                        childrenCounts[cur]++;
                        childToParent.put(graph.get(cur).get(i), cur);
                        toVisit.add(graph.get(cur).get(i));
                    }

                }

            }
        }

        LinkedList<Integer> candidates = new LinkedList<>();
        for (int i = 0; i < childrenCounts.length; ++i)
            if (childrenCounts[i] == 0)
                candidates.add(i);

        while (!candidates.isEmpty())
        {
            Integer cur = candidates.poll();
            //update parents
            if (childToParent.get(cur) != null)
            {
                int parent = childToParent.get(cur);
                hasApple.set(parent, hasApple.get(parent) | hasApple.get(cur));
                --childrenCounts[parent];
                if (childrenCounts[parent] == 0)
                    candidates.add(parent);
            }
        }

        int c = 0;
        for (int i = 0; i < hasApple.size(); ++i)
            if (hasApple.get(i))
                ++c;

        return c == 0 ? 0 : 2 * (c - 1);
    }

//    https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/discuss/624141/Clean-Python-3-peel-onion-in-O(N)-100-timespace
/*
above implementation has a bug that it might traverse reversely from the root
        int n = 5;
        int[][] edges = new int [][] {{0,1},{0,2},{1,3},{0,4}};
        List<Boolean> hasApple = Arrays.asList(false,false,false,true,false);
        System.out.println(s.minTimev3(n, edges, hasApple));
 */

    public int minTimev3(int n, int[][] edges, List<Boolean> hasApple) {

        int [] degree = new int [n];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i)
        {
            graph.putIfAbsent(edges[i][0], new ArrayList<>());
            graph.putIfAbsent(edges[i][1], new ArrayList<>());
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            ++degree[edges[i][0]];
            ++degree[edges[i][1]];
        }

        LinkedList<Integer> candidates = new LinkedList<>();
        for (int i = 0; i < degree.length; ++i)
            if (degree[i] == 1)
                candidates.add(i);

        while (!candidates.isEmpty())
        {
            Integer cur = candidates.poll();
            if (cur == 0) //think about why we need this check...
                continue;
            if (hasApple.get(cur))
                continue;
            for(Integer peer : graph.get(cur))
            {
                if(degree[peer] > 0)
                {
                    --degree[peer];
                    --degree[cur];
                    if (degree[peer] == 1)
                        candidates.add(peer);
                }

            }
        }

        int c = 0;
        for (int i = 0; i < degree.length; ++i)
            c += degree[i];
        return c;
    }

    Pair<Integer, Boolean> dfsHelper(int cur, Set<Integer> visited, List<Boolean> hasApple, Map<Integer, List<Integer>> graph)
    {
        visited.add(cur);
        boolean isSubTreeNeeded = hasApple.get(cur);
        int nodeCounts = 0;
        for (int i = 0; i < graph.get(cur).size(); ++i)
        {
            int peer = graph.get(cur).get(i);
            if (visited.contains(peer))
                continue;

            Pair<Integer, Boolean> res = dfsHelper(peer, visited, hasApple, graph);
            if (res.getValue())
            {
                isSubTreeNeeded |= true;
                nodeCounts += res.getKey();
            }
        }
        if (isSubTreeNeeded)
            ++nodeCounts; //add count for current node
        return new Pair<>(nodeCounts, isSubTreeNeeded);
    }

    public int minTimeDFS(int n, int[][] edges, List<Boolean> hasApple) {

        int [] degree = new int [n];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i)
        {
            graph.putIfAbsent(edges[i][0], new ArrayList<>());
            graph.putIfAbsent(edges[i][1], new ArrayList<>());
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            ++degree[edges[i][0]];
            ++degree[edges[i][1]];
        }

        Pair<Integer, Boolean> res =dfsHelper(0, new HashSet<>(), hasApple, graph);

        return res.getKey() == 0 ? 0 : 2 * (res.getKey() - 1);
    }


//    https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/discuss/624095/3-solutions-simple-count-%2B-DFS-%2BBFS-%2B-picture-%2B-comments-%2B-road-metaphorttps://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/discuss/624095/3-solutions-simple-count-%2B-DFS-%2BBFS-%2B-picture-%2B-comments-%2B-road-metaphor


    public static void main(String [] args) {
        Solution s = new Solution();
        int n = 7;
        int[][] edges = new int [][] {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        List<Boolean> hasApple = Arrays.asList(false,false,true,false,true,true,false);
        System.out.println(s.minTimev3(n, edges, hasApple));
//        int n = 4;
//        int[][] edges = new int [][] {{0,2},{0,3},{1,2}};
//        List<Boolean> hasApple = Arrays.asList(false,true,false,false);
//
//        int n = 5;
//        int[][] edges = new int [][] {{0,1},{0,2},{1,3},{0,4}};
//        List<Boolean> hasApple = Arrays.asList(false,false,false,true,false);
        System.out.println(s.minTimeDFS(n, edges, hasApple));
//        System.out.println(s.minTime(n, edges, hasApple));
    }
}
