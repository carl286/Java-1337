package com.l1337.l444;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    /**
     * [LeetCode] Sequence Reconstruction 序列重建


     Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
     The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4.
     Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

     Example 1:

     Input:
     org: [1,2,3], seqs: [[1,2],[1,3]]

     Output:
     false

     Explanation:
     [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
     Example 2:

     Input:
     org: [1,2,3], seqs: [[1,2]]

     Output:
     false

     Explanation:
     The reconstructed sequence can only be [1,2].
     Example 3:

     Input:
     org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

     Output:
     true

     Explanation:
     The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
     Example 4:

     Input:
     org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

     Output:
     true
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inbounds = new HashMap<>();

        Set<Integer> next = new HashSet<>();
        for (int i : org)
            next.add(i);
        int visited = 0;

        for (int i = 0; i < seqs.length; ++i) {
            Set<Integer> set = graph.getOrDefault(seqs[i][0], new HashSet<>());
            set.add(seqs[i][1]);

            inbounds.put(seqs[i][1], 1 + inbounds.getOrDefault(seqs[i][1], 0));
            next.remove(seqs[i][1]);
        }

        while (!next.isEmpty()) {
            if (next.size() != 1 || !next.contains(org[visited]))
                return false;
            next.remove(org[visited]);

            for (int i : graph.getOrDefault(org[visited], new HashSet<>())) {
                inbounds.put(i, inbounds.get(i) - 1);
                if (inbounds.get(i) == 0)
                    next.add(i);
            }

            ++visited;
        }

        return visited == org.length;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
