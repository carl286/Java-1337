package com.l1337.l547;


public class Solution {

    class UnionFind {
        private int data[];
        private int n_distinct;

        UnionFind(int n) {
            n_distinct = n;
            data = new int [n];
            for (int i = 0; i < n; ++i)
                data[i] = i;
        }

        int getParent(int i) {
            while (i != data[i]) {
                i = data[i] = data[data[i]];
            }
            return i;
        }

        void union(int i, int j) {
            int parent_i = getParent(i);
            int parent_j = getParent(j);

            if (parent_i != parent_j) {
//                data[i] = data[j] = Math.min(parent_i, parent_j);
                data[parent_j] = parent_i;
                --n_distinct;
            }
        }
    }

    public int findCircleNum(int[][] M) {
        if (M.length <= 1)
            return M.length;

        UnionFind uf = new UnionFind(M.length);
        for (int i = 0; i < M.length; ++i)
            for (int j = i + 1; j < M.length; ++j)
                if (M[i][j] == 1)
                    uf.union(i, j);

        return uf.n_distinct;
    }

//    https://leetcode.com/problems/friend-circles/discuss/101336/Java-solution-Union-Find
    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.findCircleNum(new int [][]{{1,1,0},{1,1,0},{0,0,1}}));
        System.out.println(s.findCircleNum(new int [][]{{1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},{0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},{1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},{0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},{0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}}));
    }
}
