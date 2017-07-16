package com.l1337.l773;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    private int [] base = new int [] {1, 6, 36, 216, 1296, 7776};
    private int [][] directions = new int [][] {{-1,0}, {+1, 0}, {0,-1}, {0,+1}};

    private int fromPosToIndex(int i, int j)
    {
        return i*3 + j;
    }

    private int[] fromIndexToPos(int i)
    {
        return new int []{i/3, i%3};
    }

    private int getBoardHash(int [][] board)
    {
        int total = 0;
        for(int i = 0; i < board.length; ++i)
        {
            for(int j = 0; j < board[0].length; ++j)
                total += board[i][j] * base[fromPosToIndex(i,j)];
        }
        return total;
    }
    private int [][] fromHashToBoard(int val)
    {
        int [][] ret = new int [2][3];
        for(int pos = base.length - 1; pos >= 0; --pos)
        {
            int[] index = fromIndexToPos(pos);
            ret[index[0]][index[1]] = val / base[pos];
            val %= base[pos];
        }
        return ret;
    }
    private void swapBoard(int [][] board, int x, int y, int i, int j)
    {
        int tmp = board[x][y];
        board[x][y] = board[i][j];
        board[i][j] = tmp;
    }
    private int [][] targetBoard = new int [][] {{1,2,3}, {4,5,0}};

//    https://leetcode.com/problems/sliding-puzzle/discuss/146652/Java-8ms-BFS-with-algorithm-explained
    public int slidingPuzzle(int[][] board) {
        int targetHash = getBoardHash(targetBoard);

        int initialHash = getBoardHash(board);

        if (initialHash == targetHash)
        {
            return 0;
        }

        Set<Integer> visited = new HashSet<>();
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(initialHash);
        visited.add(initialHash);

        int steps = 0;
        while(!dq.isEmpty())
        {
            ++steps;
            int size = dq.size();
            for(int t = 0; t < size; ++t)
            {
                Integer current = dq.pollFirst();
                //locate 0
                int i = -1, j = -1;
                int [][] currentBoard = fromHashToBoard(current);
                for(int x = 0; x < currentBoard.length && i == -1; ++x)
                {
                    for(int y = 0; y < currentBoard[0].length; ++y)
                    {
                        if (currentBoard[x][y] == 0)
                        {
                            i = x;
                            j = y;
                            break;
                        }
                    }
                }

                //expore
                for(int k = 0; k < directions.length; ++k)
                {
                    int next_i = i + directions[k][0];
                    int next_j = j + directions[k][1];
                    if (next_i >= 0 && next_i < currentBoard.length && next_j >= 0 && next_j < currentBoard[0].length)
                    {
                        swapBoard(currentBoard, i, j, next_i, next_j);
                        int newHash = getBoardHash(currentBoard);

                        if (!visited.contains(newHash))
                        {
                            if (newHash == targetHash)
                                return steps;

                            visited.add(newHash);
                            dq.addLast(newHash);
                        }

                        swapBoard(currentBoard, next_i, next_j, i, j);
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [][] board = new int [][] {{4,1,2},{5,0,3}};
        System.out.println(s.slidingPuzzle(board));
    }
}
