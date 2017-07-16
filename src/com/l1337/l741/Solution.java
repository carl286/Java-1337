package com.l1337.l741;


import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class Solution {

    private final int directions [][][] = new int [][][] {{{+1, 0}, {0, +1}},{{-1, 0}, {0, -1}}};

//    https://leetcode.com/articles/cherry-pickup/
//    https://leetcode.com/problems/cherry-pickup/discuss/109903/Step-by-step-guidance-of-the-O(N3)-time-and-O(N2)-space-solution
//    https://leetcode.com/problems/cherry-pickup/discuss/165218/Java-O(N3)-DP-solution-w-specific-explanation
    //dfs got TLE
    private int dfs(int acc, int startX, int startY, int endX, int endY, int [][] grid, boolean lookDown) {
        int cache = grid[startX][startY];
        acc += cache;
        int ret = -1;

        if (startX == endX && startY == endY) {
            if (lookDown) {
                grid[startX][startX] = 0;
                ret = dfs(acc, grid.length - 1, grid.length - 1, 0, 0, grid, false);

            } else {
                ret = acc;
            }
        } else {
            int localDirect = lookDown ? 0 : 1;
            grid[startX][startY] = 0;
            for (int i = 0; i < directions[localDirect].length; ++i) {
                int nextStartX = startX + directions[localDirect][i][0];
                int nextStartY = startY + directions[localDirect][i][1];


                //is valid
                if (nextStartX >= 0 && nextStartX < grid.length &&
                        nextStartY >= 0 && nextStartY < grid.length &&
                        grid[nextStartX][nextStartY] >= 0) {
                    ret = Math.max(ret, dfs(acc, nextStartX, nextStartY, endX, endY, grid, lookDown));
                }
            }
        }
        grid[startX][startY] = cache;
        return ret;
    }



//    https://leetcode.com/problems/cherry-pickup/discuss/109914/741.-Cherry-Pickup-C++-DP-and-some-comments
//    public int cherryPickup(int[][] grid) {
//
//        //DFS GOT TLE
////        //edge case
////        if (grid.length == 1)
////            return grid[0][0];
////
////        //if nothing collected, return 0 instead of -1
////        return Math.max(0, dfs(0, 0, 0, grid.length -1, grid.length - 1, grid, true));
//
//        int N = grid.length, M = (N << 1) - 1;
//        int[][] dp = new int[N][N];
//        dp[0][0] = grid[0][0];
//
//        for (int n = 1; n < M; n++) {
//            for (int i = N - 1; i >= 0; i--) {
//                for (int p = N - 1; p >= 0; p--) {
//                    int j = n - i, q = n - p;
//
//                    if (j < 0 || j >= N || q < 0 || q >= N || grid[i][j] < 0 || grid[p][q] < 0) {
//                        dp[i][p] = -1;
//                        continue;
//                    }
//
//                    if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
//                    if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
//                    if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);
//
//                    if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0);
//                }
//            }
//        }
//
//        return Math.max(dp[N - 1][N - 1], 0);
//    }


    int[][][] memo;
    int[][] grid;
    int N;
    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        N = grid.length;
        memo = new int[N][N][N];
        for (int[][] layer: memo)
            for (int[] row: layer)
                Arrays.fill(row, Integer.MIN_VALUE);
        return Math.max(0, dp(0, 0, 0));
    }
    public int dp(int r1, int c1, int c2) {
        int r2 = r1 + c1 - c2;
        if (N == r1 || N == r2 || N == c1 || N == c2 ||
                grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return -999999;
        } else if (r1 == N-1 && c1 == N-1) {
            return grid[r1][c1];
        } else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
            return memo[r1][c1][c2];
        } else {
            int ans = grid[r1][c1];
            if (c1 != c2) ans += grid[r2][c2];
            ans += Math.max(Math.max(dp(r1, c1+1, c2+1), dp(r1+1, c1, c2+1)),
                    Math.max(dp(r1, c1+1, c2), dp(r1+1, c1, c2)));
            memo[r1][c1][c2] = ans;
            return ans;
        }
    }


    public int cherryPickup2(int[][] grid) {
        int N = grid.length;
        int[][] dp = new int[N][N];
        for (int[] row: dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];

        for (int t = 1; t <= 2*N - 2; ++t) {
            int[][] dp2 = new int[N][N];
            for (int[] row: dp2) Arrays.fill(row, Integer.MIN_VALUE);

            for (int i = Math.max(0, t-(N-1)); i <= Math.min(N-1, t); ++i) {
                for (int j = Math.max(0, t-(N-1)); j <= Math.min(N-1, t); ++j) {
                    if (grid[i][t-i] == -1 || grid[j][t-j] == -1) continue;
                    int val = grid[i][t-i];
                    if (i != j) val += grid[j][t-j];
                    for (int pi = i-1; pi <= i; ++pi)
                        for (int pj = j-1; pj <= j; ++pj)
                            if (pi >= 0 && pj >= 0)
                                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + val);
                }
            }
            dp = dp2;
        }
        return Math.max(0, dp[N-1][N-1]);
    }

//    https://leetcode.com/articles/cherry-pickup/
    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.cherryPickup(new int [][] {{0, 1, -1}, {1, 0, -1}, {1, 1,  1}}));
        System.out.println(s.cherryPickup(new int [][] {{1, 0}, {-1, 0}}));
    }
}
