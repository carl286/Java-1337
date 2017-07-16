package com.l1337.l1245;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    /*
    Tree Diameter
//https://www.cnblogs.com/Dylan-Java-NYC/p/12365031.html

Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.

Example 1:

Input: edges = [[0,1],[0,2]]
Output: 2
Explanation:
A longest path of the tree is the path 1 - 0 - 2.
Example 2:

Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation:
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
     */

    public int treeDiameter(int[][] edges) {
        Set<Integer> set = new HashSet<>();
//        If it is a tree, then its nodes n = edges.length + 1
        for (int i = 0; i < edges.length; ++i)
        {
            set.add(edges[i][0]);
            set.add(edges[i][1]);
        }

        int numNodes = set.size();
        int [][] map = new int [numNodes][numNodes];

        for (int i = 0; i < edges.length; ++i)
        {
            map[edges[i][0]][edges[i][1]] = 1;
            map[edges[i][1]][edges[i][0]] = 1;
        }

        for (int i = 0; i < map.length; ++i)
        {
            for (int j = 0; j < map.length; ++j)
            {
                if (i != j && map[i][j] == 0)
                    map[i][j] = Integer.MAX_VALUE;
            }
        }

        int ret = edges.length == 0 ? 0 : 1;
        //intermediate nodes
        for (int k = 0; k < map.length; ++k)
        {
            for (int i = 0; i < map.length; ++i)
            {
                //all pairs
                for (int j = 0; j < map.length; ++j)
                {
                    //update map[i][j]
                    if (i != j && i != k && j != k && map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE)
                    {
                        map[i][j] = Math.min(map[i][j],  map[i][k] + map[k][j]);
                        ret = Math.max(ret, map[i][j]);
                    }

                }
            }

            //print
            System.out.println("k = " + k);
            for (int i = 0; i < map.length; ++i)
            {

                for (int j = 0; j < map.length; ++j)
                {
                    System.out.print(map[i][j] + "\t");
                }
                System.out.println("");
            }

        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        //System.out.println(s.treeDiameter(new int [][] {{0, 1}, {0,2}}));
        System.out.println(s.treeDiameter(new int [][] {{0, 1}, {1,2}, {2,3}, {1,4}, {4,5}}));
    }
}
