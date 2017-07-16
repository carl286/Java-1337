package com.l1337.l685;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/redundant-connection-ii/discuss/108058/one-pass-disjoint-set-solution-with-explain
//    https://leetcode.com/problems/redundant-connection-ii/discuss/141897/Logical-Thinking-with-Java-Code-Beats-96.71
//    https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C++Java-Union-Find-with-explanation-O(n)
//    https://leetcode.com/problems/redundant-connection-ii/discuss/108070/Python-O(N)-concise-solution-with-detailed-explanation-passed-updated-testcases
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> outDegrees = new HashMap<>();
        Set<Integer> roots = new HashSet<>();

        Integer badChild = -1;
        for (int i = 0; i < edges.length; ++i) {
            Set<Integer> parent = map.getOrDefault(edges[i][1], new HashSet<>());
            parent.add(edges[i][0]);
            map.put(edges[i][1],  parent);
            roots.remove(edges[i][1]);

            outDegrees.put(edges[i][0], outDegrees.getOrDefault(edges[i][0], 0) + 1);

            if (parent.size() >= 2) {
                badChild = edges[i][1];
            }

            if (map.getOrDefault(edges[i][0], new HashSet<>()).size() == 0)
                roots.add(edges[i][0]);

            if (roots.size() == 0)
                return edges[i];
        }

        //must have bad child
        Integer [] parents = map.get(badChild).toArray(new Integer [0]);
        int k = 0;
        while (k < parents.length) {
            if (outDegrees.getOrDefault(parents[k], 0) == 1 && roots.contains(parents[k]))
                break;
            ++k;
        }
        if (k != parents.length) {
            return new int [] {parents[1-k], badChild};
        }

        //search from backwards
        for (int i = edges.length - 1; i >= 0; --i) {
            if  (((edges[i][0] == parents[0]) || (edges[i][0] == parents[1])) && (edges[i][1] == badChild))
                return edges[i];
         }

        return null;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
