package com.l1337.l130;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int hashCode() {
            int ret = 17;
            ret = 31 * ret + x;
            ret = 31 * ret + y;
            return ret;
        }
        public boolean equals(Object that) {
            Node n = (Node) that;
            return n.x == x && n.y == y;
        }
    }

    private char C = 'C';

    private void Add(HashSet<Node> visited, LinkedList<Node> queue, char[][] board, int x, int y)
    {
        if (board[x][y] != 'O')
            return;

        board[x][y] = 'C';
        Node n = new Node(x,y);
        visited.add(n);
        queue.add(n);

        while (!queue.isEmpty())
        {
            Node current = queue.remove(0);

            //expand
            if (current.x - 1 >= 0 && board[current.x-1][current.y] == 'O')
            {
                Add(visited, queue, board, current.x-1, current.y);
            }

            if (current.x + 1 < board.length && board[current.x + 1][current.y] == 'O')
            {
                Add(visited, queue, board, current.x + 1, current.y);
            }

            if (current.y - 1 >= 0 && board[current.x][current.y-1] == 'O')
            {
                Add(visited, queue, board, current.x, current.y-1);
            }

            if (current.y + 1 < board[0].length && board[current.x][current.y+1] == 'O')
            {
                Add(visited, queue, board, current.x, current.y+1);
            }
        }
    }

    public void solve(char[][] board) {
        if (board.length <= 2 || board[0].length <= 2)
            return;

        HashSet<Node> visited = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        for (int i = 0; i < board.length; ++i)
        {
            Add(visited, queue, board, i, 0);
            Add(visited, queue, board, i, board[0].length-1);
        }

        for (int i = 0; i < board[0].length; ++i)
        {
            Add(visited, queue, board, 0, i);
            Add(visited, queue, board, board.length-1, i);
        }

        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[0].length; ++j)
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'C')
                    board[i][j] = 'O';
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
