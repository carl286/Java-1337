package com.l1337.l675;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    static class Node {
        int val;
        int i;
        int j;
        Node(int v, int i, int j) {
            this.val = v;
            this.i = i;
            this.j = j;
        }
    }

    private int getHash(int i, int j, int n) {
        return i * n + j;
    }

//    https://leetcode.com/problems/cut-off-trees-for-golf-event/discuss/107404/Java-solution-PriorityQueue-+-BFS
    //analyze your complexity before you go crazy..
    public int cutOffTreefloyd(List<List<Integer>> forest) {
        //I assume first node is always valid here...
        int m = forest.size();
        if (m <= 0)
            return 0;
        int n = forest.get(0).size();
        if (n <= 0)
            return 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });

        int dp [][] = new int [m*n][m*n];
        for (int i = 0; i < dp.length; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //(i,j) => i * n + j

        for (int i = 0; i < forest.size(); ++i) {
            List<Integer> list = forest.get(i);
            for (int j = 0; j < list.size(); ++j) {
                if (list.get(j) > 1) {
                    pq.add(new Node(list.get(j), i, j));
                }
                int hash0 = getHash(i,j,n);
                dp[hash0][hash0] = 0;
                if (j + 1 < list.size()) {
                    int hash1 = getHash(i, j + 1, n);
                    if (list.get(j) > 0 && list.get(j+1) > 0) {
                        dp[hash0][hash1] = 1;
                        dp[hash1][hash0] = 1;
//                    } else {
//                        dp[hash0][hash1] = Integer.MAX_VALUE;
//                        dp[hash1][hash0] = Integer.MAX_VALUE;
                    }
                }

                if (i + 1 < forest.size()) {
                    int hash2 = getHash(i + 1, j, n);
                    if (list.get(j) > 0 && forest.get(i+1).get(j) > 0) {
                        dp[hash0][hash2] = 1;
                        dp[hash2][hash0] = 1;
//                    } else {
//                        dp[hash0][hash2] = Integer.MAX_VALUE;
//                        dp[hash2][hash0] = Integer.MAX_VALUE;
                    }
                }
            }
        }


        //floyd wasshal
        for (int k = 0; k < dp.length; ++k) {
            for (int i = 0; i < dp.length; ++i) {
                for (int j = 0; j < dp.length; ++j) {
                    if (dp[i][k] != Integer.MAX_VALUE &&
                            dp[k][j] != Integer.MAX_VALUE &&
                            dp[i][j] > dp[i][k] + dp[k][j])
                        dp[i][j] = dp[i][k] + dp[k][j];
                }
            }
        }

        int ret = 0;
        int lastHash = getHash(0,0,n);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curHash = getHash(cur.i, cur.j, n);
            if (dp[lastHash][curHash] == Integer.MAX_VALUE)
                return -1;

            ret += dp[lastHash][curHash];
            lastHash = curHash;
        }

        return ret;
    }

    static int[][] dir = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return 0;
        int m = forest.size(), n = forest.get(0).size();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    pq.add(new int[] {i, j, forest.get(i).get(j)});
                }
            }
        }

        int[] start = new int[2];
        int sum = 0;
        while (!pq.isEmpty()) {
            int[] tree = pq.poll();
            int step = minStep(forest, start, tree, m, n);

            if (step < 0) return -1;
            sum += step;

            start[0] = tree[0];
            start[1] = tree[1];
        }

        return sum;
    }

//    http://users.eecs.northwestern.edu/~haizhou/357/lec6.pdf
//    https://leetcode.com/problems/cut-off-trees-for-golf-event/discuss/107396/Python-solution-based-on-wufangjie's-(Hadlock's-algorithm)
    private int minStep(List<List<Integer>> forest, int[] start, int[] tree, int m, int n) {
        int step = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == tree[0] && curr[1] == tree[1]) return step;

                for (int[] d : dir) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n
                            || forest.get(nr).get(nc) == 0 || visited[nr][nc]) continue;
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
            step++;
        }

        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        List<Integer> l1 = Arrays.asList(1,3,4);
        List<Integer> l2 = Arrays.asList(0,0,5);
        List<Integer> l3 = Arrays.asList(8,7,6);
        List<List<Integer>> l = Arrays.asList(l1,l2,l3);

        System.out.println(s.cutOffTree(l));
    }
}
