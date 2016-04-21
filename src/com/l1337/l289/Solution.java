package com.l1337.l289;


//https://leetcode.com/problems/game-of-life/
//289. Game of Life
public class Solution {

    private final int LOWPART = 0x1;
    private final int HIGHPART = (1<<4);
    private final int[][] directions = {{-1,-1}, {-1,0}, {-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    public void gameOfLife(int[][] board) {

        //get next round information
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[i].length; ++j) {
                int count = 0;
                for (int k = 0; k < directions.length; ++k) {
                    int nx = i + directions[k][0];
                    int ny = j + directions[k][1];
                    if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[i].length) {
                        if ((board[nx][ny] & LOWPART) != 0) {
                            ++count;
                        }
                    }
                }

                if (count == 3) {
                    board[i][j] |= HIGHPART;
                } else if (count == 2 && (board[i][j]& LOWPART) != 0) {
                    board[i][j] |= HIGHPART;
                } else {
                    board[i][j] &= ~HIGHPART;
                }
            }

        //get the latest data
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[i].length; ++j) {
                board[i][j] = (board[i][j] & HIGHPART) >> 4;
            }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
