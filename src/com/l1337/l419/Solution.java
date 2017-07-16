package com.l1337.l419;


public class Solution {

//    题目大意：水平方向或者竖直方向上的'X'集合算一条军舰，不能拐弯，不会出现两条军舰挨着的情况。
//    https://leetcode.com/problems/battleships-in-a-board/description/

//    https://leetcode.com/problems/battleships-in-a-board/discuss/90902/Simple-Java-Solution
    private static final int [][] directions = {{-1, 0}, {+1, 0}, {0, -1}, {0, +1}};
    public int countBattleships(char[][] board) {

        int ret = 0;
        for (int i = 0; i < board.length; ++i) {
            int j = 0;
            while (j < board[i].length) {
                if (board[i][j] == 'X') {
                    board[i][j] = 'C';
                    System.out.println(i + "\t" + j);
                    //vertical
                    int k = i;
                    while (k + 1 < board.length && board[k+1][j] == 'X') {
                        board[++k][j] = 'C';
                    }
                    //horizontal
                    while (j + 1< board[i].length && board[i][j+1] == 'X') {
                        board[i][++j] = 'C';
                    }
                    ++ret;
                }

                ++j;
            }
        }


        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        char board[][] = {{'.','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        System.out.println(s.countBattleships(board));
    }
}
