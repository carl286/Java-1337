package com.l1337.l743;


import java.util.*;

public class Solution {

//    https://leetcode.com/problems/network-delay-time/
    public int networkDelayTime(int[][] times, int N, int K) {
        //src -> (dst -> weight)
        Map<Integer, Map<Integer, Integer>> originalEdges = new HashMap<>();
        for (int i = 0; i < times.length; ++i)
        {
            Map<Integer, Integer> child;
            if (!originalEdges.containsKey(times[i][0]))
            {
                child = new HashMap<>();
                originalEdges.put(times[i][0], child);
            }
            else
            {
                child = originalEdges.get(times[i][0]);
            }

            child.put(times[i][1], times[i][2]);
        }

        //data structure to get the least weight dsts....
        //weight (from k to the node), set of dsts
        TreeMap<Integer, Set<Integer>> routes = new TreeMap<>();

        //dst -> weight
        Map<Integer, Integer> previews = new HashMap<>();
        //init routes
        for(Map.Entry<Integer, Integer> entry : originalEdges.getOrDefault(K, new HashMap<>()).entrySet())
        {
            Set<Integer> set;
            if (routes.containsKey(entry.getValue()))
            {
                set = routes.get(entry.getValue());
            }
            else
            {
                set = new HashSet<>();
                routes.put(entry.getValue(), set);
            }

            //weight -> dst
            set.add(entry.getKey());
            //dst -> weight
            previews.put(entry.getKey(), entry.getValue());
        }

        Set<Integer> finalized = new HashSet<>();
        finalized.add(K);

        int ret = -1;
        while (!routes.isEmpty())
        {
            //pick up the one with the least weight...
            Map.Entry<Integer, Set<Integer>> entry = routes.firstEntry();
            //pick an dst
            Integer weight = entry.getKey();
            ret = weight;
            Integer dst = entry.getValue().iterator().next();
            entry.getValue().remove(dst);

            //it's empty now
            if (entry.getValue().size() == 0)
            {
                routes.remove(weight);
            }

            //delete the latest one from preview
            previews.remove(dst);
            finalized.add(dst);

            //update expansion
            for(Map.Entry<Integer, Integer> children : originalEdges.getOrDefault(dst, new HashMap<>()).entrySet())
            {
                int nextHop = children.getKey();
                if (!finalized.contains(nextHop))
                {
                    int newWeight = weight + children.getValue();
                    int oldWeight = previews.getOrDefault(nextHop, Integer.MAX_VALUE);

                    if (oldWeight > newWeight)
                    {
                        previews.put(nextHop, newWeight);
                        Set<Integer> old = routes.get(oldWeight);
                        if(old != null)
                        {
                            old.remove(nextHop);
                            if (old.isEmpty())
                                routes.remove(oldWeight);
                        }
                        //add to new weight
                        Set<Integer> newSet = routes.getOrDefault(newWeight, new HashSet<>());
                        newSet.add(nextHop);
                        routes.put(newWeight, newSet);
                    }
                }
            }
        }


        return finalized.size() == N ?  ret : -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();

//        System.out.println(s.networkDelayTime(new int [][] {{2,1,1}, {2,3,1}, {3,4,1}}, 4, 2));
//        System.out.println(s.networkDelayTime(new int [][] {{1,2,1}}, 2, 1));
//        System.out.println(s.networkDelayTime(new int [][] {{1,2,1}}, 2, 2));
        System.out.println(s.networkDelayTime(new int [][] {{1,2,1}, {2,1,3}}, 2, 2));
    }
}
