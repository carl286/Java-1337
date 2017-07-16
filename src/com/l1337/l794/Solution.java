package com.l1337.l794;


public class Solution {

    private boolean isCWin(String[] board, char c)
    {
        //row
        for(int i = 0; i < board.length; ++i)
        {
            boolean win = true;
            for(int j = 0; j < board[i].length(); ++j)
            {
                if (board[i].charAt(j) != c)
                {
                    win = false;
                    break;
                }
            }
            if (win)
                return true;
        }

        //column
        for(int i = 0; i < board[0].length(); ++i)
        {
            boolean win = true;
            for(int j = 0; j < board.length; ++j)
            {
                if (board[j].charAt(i) != c)
                {
                    win = false;
                    break;
                }
            }
            if (win)
                return true;
        }

        //diag
        boolean win = true;
        for(int i = 0; i < board.length; ++i)
        {

            if (board[i].charAt(i) != c)
            {
                win = false;
                break;
            }
        }
        if (win)
            return true;

        //anti-diag
        win = true;
        for(int i = 0; i < board.length; ++i)
        {
            if (board[i].charAt(board.length - 1 -i) != c)
            {
                win = false;
                break;
            }
        }
        if (win)
            return true;

        return false;
    }
    public boolean validTicTacToe(String[] board) {
        int XCount = 0, OCount = 0;
        for(int i = 0; i < board.length; ++i)
        {
            for(int j = 0; j < board[i].length(); ++j)
            {
                switch (board[i].charAt(j))
                {
                    case 'O':
                        ++OCount;
                        break;
                    case 'X':
                        ++XCount;
                        break;
                    default:
                        break;
                }
            }
        }

        if (XCount < OCount || XCount > 1 + OCount)
            return false;
        boolean isXWin = isCWin(board, 'X');
        boolean isOWin = isCWin(board, 'O');
        if (isXWin && isOWin)
            return false;

        if (isXWin && XCount == OCount)
            return false;
        if (isOWin && XCount > OCount)
            return false;
        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String[] board = new String [] {"XOX", "O O", "XOX"};
        System.out.println(s.validTicTacToe(board));
    }
}
