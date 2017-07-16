package com.l1337.l886;


import java.util.*;

public class Solution {

    private boolean possibleBipartitionHelper(Set<Integer> left, Set<Integer> right, Map<Integer, Set<Integer>> map, int index, int lastIndex) {
        if (index > lastIndex)
            return true;
        //try fit index
        {
            Set<Integer> conflict = map.getOrDefault(index, new HashSet<>());
//            if(left.contains(index) || right.contains(index))
//                return possibleBipartitionHelper(left, right, map, index + 1, lastIndex);
            //pick one for index
            boolean canPutLeft = true, canPutRight = true;
            for (Integer entry : conflict) {
                if (left.contains(entry)) {
                    canPutLeft = false;
                }
                if (right.contains(entry)) {
                    canPutRight = false;
                }
            }
            if (canPutLeft) {
                if (canPutRight) {
                    //try left first
                    left.add(index);
                    if (!possibleBipartitionHelper(left, right, map, index + 1, lastIndex)) {
                        left.remove(index);

                        //try right
                        right.add(index);
                        return possibleBipartitionHelper(left, right, map, index + 1, lastIndex);
                    }
                    else
                    {
                        return true;
                    }
                } else {
                    left.add(index);
                    return possibleBipartitionHelper(left, right, map, index + 1, lastIndex);
                }

            } else if (canPutRight) {
                right.add(index);
                return possibleBipartitionHelper(left, right, map, index + 1, lastIndex);
            } else {
                return false;
            }
        }
    }
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        //Considering optimization...
        for (int i = 0; i < dislikes.length; ++i)
        {
            if(dislikes[i][0] != dislikes[i][1])
            {
                map.putIfAbsent(dislikes[i][0], new HashSet<>());
                map.get(dislikes[i][0]).add(dislikes[i][1]);

                map.putIfAbsent(dislikes[i][1], new HashSet<>());
                map.get(dislikes[i][1]).add(dislikes[i][0]);
            }
        }

        Set<Integer> left = new HashSet<>(), right = new HashSet<>();
        left.add(1);
        return possibleBipartitionHelper(left, right, map, 2, N);
    }


    List<List<Integer>> graph = new ArrayList<>();
    Map<Integer, Integer> color = new HashMap<>();

    public boolean possibleBipartition_v2(int N, int[][] dislikes) {
        for (int i = 1; i <= N + 1; ++i) graph.add(new ArrayList());

        for (int[] edge : dislikes) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int node = 1; node <= N; ++node) {
//            if (!color.containsKey(node) && !dfs(node, 0)) return false;
            if (!color.containsKey(node) && !dfs(node, 0)) return false;
        }

        return true;
    }

    private boolean dfs(int node, int color) {
        if (this.color.containsKey(node)) return this.color.get(node) == color;

        this.color.put(node, color);

        for (int neighbor : graph.get(node)) {
            if (!dfs(neighbor, color ^ 1)) return false;
        }

        return true;
    }

//    https://leetcode.com/problems/possible-bipartition/discuss/654887/C%2B%2B-BFS-with-detailed-explanation
    public static void main(String [] args) {
        Solution s = new Solution();
        int N = 4;
        int [][] dislikes = new int [][] {{1,2}, {1,3}, {2,4}};
//        int N = 3;
//        int [][] dislikes = new int [][] {{1,2}, {1,3}, {2,3}};
//        int N = 5;
//        int [][] dislikes = new int [][] {{1,2}, {2,3}, {3,4}, {4,5}, {1,5}};
        System.out.println(s.possibleBipartition_v2(N, dislikes));
    }
}
