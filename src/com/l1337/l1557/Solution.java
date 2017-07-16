package com.l1337.l1557;


import java.util.*;

public class Solution {

//    https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/discuss/805685/JavaC%2B%2BPython-Nodes-with-no-In-Degree
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> ret = new ArrayList<>();
        Set<Integer> unvisited = new HashSet<>();

        //heads only stores whose inbound is 0...
        Set<Integer> heads = new HashSet<>();
        for (int i = 0; i < n; ++i)
        {
            unvisited.add(i);
            heads.add(i);
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.size(); ++i)
        {
            if (edges.get(i).get(0) != edges.get(i).get(1))
            {
                Set<Integer> next = graph.getOrDefault(edges.get(i).get(0), new HashSet<>());
                next.add(edges.get(i).get(1));
                graph.put(edges.get(i).get(0), next);
                heads.remove(edges.get(i).get(1));
            }
        }

        while (!unvisited.isEmpty())
        {
            Integer cur;
            //pick up one from heads first
            if (heads.isEmpty())
            {
                cur = unvisited.iterator().next();
                //unvisited.remove(cur);
            }
            else
            {
                cur = heads.iterator().next();
                heads.remove(cur);
            }

            ret.add(cur);
            //visit all children of cur;
            unvisited.remove(cur);
            //GOT TLE.... for DTS
            // dfs(cur, unvisited, graph);
            ArrayDeque<Integer> toExpand = new ArrayDeque<>();
            toExpand.add(cur);
            while (!toExpand.isEmpty())
            {
                Integer current = toExpand.pollFirst();
                Set<Integer> next = graph.getOrDefault(current, new HashSet<>());
                for(Integer i: next)
                {
                    if (unvisited.contains(i))
                    {
                        unvisited.remove(i);
                        toExpand.add(i);
                    }
                }

            }
        }

        return ret;
    }

    private void dfs(int current, Set<Integer> unvisited, Map<Integer, Set<Integer>> graph)
    {
        Set<Integer> next = graph.getOrDefault(current, new HashSet<>());
        for(Integer i: next)
        {
            if (unvisited.contains(i))
            {
                unvisited.remove(i);
                dfs(i, unvisited, graph);
            }
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();

        int n = 6;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0,1));
        edges.add(Arrays.asList(0,2));
        edges.add(Arrays.asList(2,5));
        edges.add(Arrays.asList(3,4));
        edges.add(Arrays.asList(4,2));

//        int n = 3;
//        List<List<Integer>> edges = new ArrayList<>();
//        edges.add(Arrays.asList(1,1));
//        edges.add(Arrays.asList(1,2));
//        edges.add(Arrays.asList(0,0));

        System.out.println(s.findSmallestSetOfVertices(n, edges));
    }
}
