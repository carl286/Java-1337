package com.l1337.l576;


import java.util.Arrays;

public class Solution {
    private final int [][] directions = {{-1,0}, {1,0}, {0,-1}, {0,+1}};
    private final int divisor = 1000000007;

    private boolean isInBound(int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }

    //N >= 0
    private int dfs (int i, int j, int m, int n, int N) {
        if (!isInBound(i,j,m,n)) {
//            System.out.println("found: " + i + "\t" + j);
            return 1;
        }

        int ret = 0;

        if (N > 0) {
            for (int k = 0; k < directions.length; ++k) {
                int next_i = i + directions[k][0];
                int next_j = j + directions[k][1];
                ret = (ret + dfs(next_i, next_j, m, n, N - 1) % divisor) % divisor;
            }
        }

        return ret;
    }


    private long dfsWithMemo (int i, int j, int m, int n, int N, long [][][] memo) {
        if (!isInBound(i,j,m,n)) {
//            System.out.println("found: " + i + "\t" + j);
            if (N >= 0)
                return 1;
            else
                return 0;
        }

        long ret = 0;

        if (N > 0) {
            if (memo[i][j][N-1] >= 0) {
                ret = memo[i][j][N-1];
            } else {
                for (int k = 0; k < directions.length; ++k) {
                    int next_i = i + directions[k][0];
                    int next_j = j + directions[k][1];
//                    ret += dfsWithMemo(next_i, next_j, m, n, N - 1, memo);
                    ret = (ret + dfsWithMemo(next_i, next_j, m, n, N - 1, memo) % divisor) % divisor;
                }
                memo[i][j][N-1] = ret;
            }
        }

        return ret;
    }

    private int dfsWithDp (int i, int j, int m, int n, int N) {
        long [][][] memo = new long[m][n][N];

        //init row
        for (int k = 0; k < n; ++k) {
            memo[0][k][0] = 1;
            memo[m-1][k][0] += 1;
        }

        //init col
        for (int k = 0; k < m; ++k) {
            memo[k][0][0] += 1;
            memo[k][n-1][0] += 1;
        }

        for (int k = 1; k < N; ++k) {
            for (int x = 0; x < m; x++)
                for (int y = 0; y < n; y++) {
                //memo[x][y][k]
                    for (int t = 0; t < directions.length; ++t) {
                        int next_i = x + directions[t][0];
                        int next_j = y + directions[t][1];
                        if (!isInBound(next_i, next_j, m, n))
                            memo[x][y][k] = (memo[x][y][k] + 1 ) % divisor;
                        else
                            memo[x][y][k] = (memo[x][y][k] + memo[next_i][next_j][k-1]) % divisor;
                    }
                }
        }

        return (int) memo[i][j][N-1];
    }

    public int findPaths(int m, int n, int N, int i, int j) {

        //        return dfs(i,j,m,n,N);



//        long [][][] memo = new long[m][n][N];
//        for (int x = 0; x < m; ++x)
//            for (int y = 0; y < n; ++y)
//                Arrays.fill(memo[x][y], -1);
//        return (int)dfsWithMemo(i,j,m,n,N,memo);

        if (N <= 0)
            return 0;

        return dfsWithDp(i,j,m,n,N);
//        return 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int m = 2, n = 2, N = 2, i = 0, j = 0;
//
//        int m = 8, n = 50, N = 23, i = 5, j = 26;
        System.out.println(s.findPaths(m,n,N,i,j));
    }
}
