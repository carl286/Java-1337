package com.l1337.l323;

//	Number of Connected Components in an Undirected Graph, 323
//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
//	Example 1:
//			0          3
//			|          |
//			1 --- 2    4
//	Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
//	Example 2:
//			0           4
//			|           |
//			1 --- 2 --- 3
//	Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
//	Note:
//	You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


public class Solution {

    //	https://leetcode.com/discuss/76594/short-union-find-in-python-ruby-c
//	http://algs4.cs.princeton.edu/lectures/15UnionFind.pdf
    //	http://algs4.cs.princeton.edu/15uf/WeightedQuickUnionUF.java.html
    public class WeightedQuickUnionUF {
        private int[] parent;   // parent[i] = parent of i
        private int[] size;     // size[i] = number of sites in subtree rooted at i
        private int count;      // number of components

        public WeightedQuickUnionUF(int N) {
            count = N;
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            //This part is Cool,,,, flatten the tree while finding the parent
            while (p != parent[p])
                p = parent[p];
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make smaller root point to larger one
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            //COOL. Why not get component number on the way...
            count--;
        }
    }

    public int countComponents(int n, int[][] edges) {
        if (n <= 0 || edges == null)
            return 0;

//		Quick union with path compression. Just after computing the root of p, set the id[] of each examined node to point to that root.
//		Two-pass implementation: add second loop to find() to set the id[] of each examined node to the root.
//		Simpler one-pass variant (path halving): Make every other node in path point to its grandparent.
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        for (int i = 0; i < edges.length; i++)
            uf.connected(edges[i][0], edges[i][1]);

        return uf.count();
    }

    //	public int countComponents(int n, int[][] edges) {
//		int[] p = new int[n];
//		for (int i=0; i<n; i++)
//			p[i] = i;
//		for (int[] edge : edges) {
//			int v = edge[0], w = edge[1];
//			while (p[v] != v) v = p[v] = p[p[v]];
//			while (p[w] != w) w = p[w] = p[p[w]];
//			p[v] = w;
//			if (v != w)
//				n--;
//		}
//		return n;
//	}

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
