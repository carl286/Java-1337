package com.l1337.l417;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {

    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            Node that = (Node) obj;
            return this.x == that.x && this.y == that.y;
        }
        public int hashCode() {
            return 151 * x + y;
        }

    }

    public static final int NO_WHERE = 0x00;
    public static final int PACIFIC = 0x01;
    public static final int ATLANTIC = 0x10;
    public static final int ALL_REACHED = 0x11;

    private static final int [][] directions = {{-1, 0}, {+1, 0}, {0, -1}, {0, +1}};

    private int get_access_code(Node n, int [][] matrix) {
        int code = NO_WHERE;
        if (n.x == 0 || n.y == 0)
            code |= PACIFIC;

        if (n.x == matrix.length - 1 || n.y == matrix[0].length - 1)
            code |= ATLANTIC;
        return code;
    }

    private boolean is_valide_coordinate(int x, int y, int [][] matrix) {
        return (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length);
    }

    //stack overflow for dfs
    private void dfs (Node n, int [][] matrix, Set<Node> visited, int [][] codes, List<int []> ret) {
        if (visited.contains(n))
            return;

        visited.add(n);
        codes[n.x][n.y] |= get_access_code(n, matrix);

        if (codes[n.x][n.y] != ALL_REACHED) {
            for (int k = 0; k < directions.length; ++k) {
                int x = n.x + directions[k][0];
                int y = n.y + directions[k][1];
                if (is_valide_coordinate(x, y ,matrix)) {
                    Node next = new Node(x, y);
                    dfs(next, matrix, visited, codes, ret);
                    if (matrix[n.x][n.y] >= matrix[next.x][next.y]) {
                        codes[n.x][n.y] |= codes[next.x][next.y];
                        if (codes[n.x][n.y] == ALL_REACHED) return;
                    }
                }
            }
        }
    }

    public List<int[]> pacificAtlanticDFS(int[][] matrix) {
        int [][] codes = new int[matrix.length][];
        for (int i = 0; i < codes.length; ++i)
            codes[i] = new int [matrix[0].length];

        List<int []> ret = new ArrayList<>();

        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j) {
                Node n = new Node(i, j);
                Set<Node> visited = new HashSet<>(); //nodes that I touched already for DFS
                dfs(n, matrix, visited, codes, ret);
                if (codes[n.x][n.y] == ALL_REACHED)
                    ret.add(new int []{n.x, n.y});
            }

        return ret;
    }

    private int bfs (Node n, int [][] matrix) {
        LinkedList<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        visited.add(n);
        queue.add(n);
        int reached = NO_WHERE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            reached |= get_access_code(cur, matrix);
            if (reached == ALL_REACHED)
                return ALL_REACHED;

            for (int k = 0; k < directions.length; ++k) {
                int x = cur.x + directions[k][0];
                int y = cur.y + directions[k][1];
                if (is_valide_coordinate(x, y ,matrix) && matrix[cur.x][cur.y] >= matrix[x][y]) {
                    Node next = new Node(x, y);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        return reached;
    }
    public List<int[]> pacificAtlanticBFS(int[][] matrix) {
        List<int []> ret = new ArrayList<>();

        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j) {
                Node n = new Node(i, j);
                if (bfs(n, matrix) == ALL_REACHED)
                    ret.add(new int []{n.x, n.y});
            }

        return ret;
    }

    private int bfs2 (Node n, int [][] matrix, Set<Node> possible) {
        LinkedList<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        visited.add(n);
        queue.add(n);
        int reached = NO_WHERE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            reached |= get_access_code(cur, matrix);
            if (reached == ALL_REACHED)
                return ALL_REACHED;

            for (int k = 0; k < directions.length; ++k) {
                int x = cur.x + directions[k][0];
                int y = cur.y + directions[k][1];
                if (is_valide_coordinate(x, y ,matrix) && matrix[cur.x][cur.y] >= matrix[x][y]) {
                    Node next = new Node(x, y);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        return reached;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        int[][] matrix = {{1,1,1} , {1,2,1}, {1,1,1}};
        int [][] matrix = {{1,2,2, 3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
//        int[][] matrix = {{1,1,1} , {1,1,1}};
        System.out.println("Hello World");
        for (int [] pair : s.pacificAtlanticBFS(matrix)) {
            System.out.println(pair[0] + "\t" + pair[1]);
        }
    }
}
