package com.l1337.l684;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            Set<Integer> s0 = map.getOrDefault(edges[i][0], new HashSet<>());
            s0.add(edges[i][1]);
            map.put(edges[i][0], s0);

            Set<Integer> s1 = map.getOrDefault(edges[i][1], new HashSet<>());
            s1.add(edges[i][0]);
            map.put(edges[i][1], s1);
        }

        while (true) {

            boolean noprogress = true;

            Set<Integer> nodesToMove = new HashSet<>();
            for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
                int key = entry.getKey();
                Set<Integer> val = entry.getValue();
                if (val.size() == 1) {
                    noprogress = false;
                    Integer [] d = val.toArray(new Integer [0]);
                    map.get(d[0]).remove(key);
                    nodesToMove.add(key);
                    if (map.get(d[0]).size() == 0)
                        nodesToMove.add(d[0]);
                }
            }

            for (Integer n : nodesToMove) {
                map.remove(n);
            }

            if (noprogress)
                break;
        }


        Set<Integer> connected  = map.keySet();
        for (int i = edges.length - 1; i >= 0; --i) {
            if (connected.contains(edges[i][0]) && connected.contains(edges[i][1])) {
                return new int [] {edges[i][0], edges[i][1]};
            }
        }
        return null;
    }

//    https://leetcode.com/problems/redundant-connection/discuss/107984/10-line-Java-solution-Union-Find.
//    https://leetcode.com/problems/redundant-connection/discuss/108002/Unicode-Find-(5-short-lines)
//    https://leetcode.com/problems/redundant-connection/discuss/123819/Beats-97.96-Union-Find-Java-with-Explanations
//    https://leetcode.com/problems/redundant-connection/discuss/107985/Java.-Elegant-Solution-Beats-92-Exploits-Problem-Statement
    public static void main(String [] args) {
        Solution s = new Solution();
//        int ret [] = s.findRedundantConnection(new int [][]{{1,2}, {1,3}, {2,3}});
//        int ret [] = s.findRedundantConnection(new int [][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}});
        int ret [] = s.findRedundantConnection(new int [][]{{9,10},{5,8},{2,6},{1,5},{3,8},{4,9},{8,10},{4,10},{6,8},{7,9}});
        System.out.print(ret[0] + "\t" + ret[1]);
    }
}
