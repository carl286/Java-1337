package com.l1337.l526;


public class Solution {

    private final int [][] map = new int [][] {
            {}, //0 -> Nill
            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}, // 1 to all
            {1,2,4,6,8,10,12,14}, // 2 to evens
            {1,3,6,9,12,15}, //3
            {1,2,4,8,12},  //4
            {1,5,10,15}, //5
            {1,2,3,6,12}, //6
            {1,7,14}, //7
            {1,2,4,8}, //8
            {1,3,9}, //9
            {1,2,5,10}, //10
            {1,11}, //11
            {1,2,3,4,6,12}, //12
            {1,13}, //13
            {1,2,7,14}, //14
            {1,3,5,15}, //15
    };

    private int dfs(boolean free [], int depth) {
        if (depth >= free.length) {
            return 1;
        }

        int ret = 0;

        for (int k = 0; k < map[depth].length && map[depth][k] < free.length; ++k) {
            if (free[map[depth][k]]) {
                free[map[depth][k]] = false;

                ret += dfs(free, 1 + depth);

                free[map[depth][k]] = true;
            }
        }

        return ret;
    }

    public int countArrangement(int N) {

        boolean free [] = new boolean[N+1];
        for (int i = 0; i < free.length; ++i)
            free[i] = true;

        return dfs(free, 1);
    }

//    https://leetcode.com/problems/beautiful-arrangement/discuss/99723/Java-DP-solution.
    //Think how to use memo, trick is on how ot hash them...
    public static void main(String [] args) {
        Solution s = new Solution();
        for (int i = 0; i <= 15; ++i)
            System.out.println(s.countArrangement(i));
    }
}
