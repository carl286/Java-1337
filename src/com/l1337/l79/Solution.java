package com.l1337.l79;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private int [][] directions = new int [][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean helper(char[][] board, int i, int j, String word, int k, boolean [][] visited) {
        if (k == word.length())
            return true;

        if (i >= 0 && i < board.length && j >= 0 && j < board[i].length && board[i][j] == word.charAt(k) && !visited[i][j]) {
            visited[i][j] = true;
            for (int t = 0; t < directions.length; ++t) {
                if (helper(board, i + directions[t][0], j + directions[t][1], word, k + 1, visited)) {
                    visited[i][j] = false;
                    return true;
                }
            }
            visited[i][j] = false;
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length <= 0 || board[0].length <= 0||
            word == null || word.length() <= 0)
            return false;

        boolean [][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (helper(board, i, j, word, 0, visited))
                    return true;
            }
        }

        return false;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
