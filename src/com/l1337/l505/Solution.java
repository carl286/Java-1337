package com.l1337.l505;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {

    //    https://leetcode.com/articles/the-maze-ii/

    private static int [][] directions = {{-1,0}, {0,-1}, {0,+1}, {+1,0}};
    //assume start and dest won't be wall, they're not the same
    static class Node {
        int x;
        int y;
        //        boolean [] availableDirectiosn;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
//            availableDirectiosn = new boolean[4];
//            for (int i = 0; i < availableDirectiosn.length; ++i)
//                availableDirectiosn[i] = true;
        }

        public boolean equals(Object that) {
            Node t = (Node) that;
            return t.x == x && t.y == y;
        }

        public int hashCode() {
            return x + y;
        }
    }

    private boolean isEmptySpace(int[][] maze, int x, int y) {
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length)
            return maze[x][y] == 0;
        return false;
    }

    //I like the trick to use priority queue though it still has some bugs....
//    https://leetcode.com/articles/the-maze-ii/#approach-4-using-dijkstra-algorithm-and-priority-queueaccepted
//    http://www.cnblogs.com/grandyang/p/6725380.html
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        if (start[0] == dest[0] && start[1] == dest[1])
            return 0;

        Map<Node, boolean []> visited = new HashMap<>();
        Deque<Node> q = new LinkedList<>();
        Node n = new Node(start[0], start[1]);
        q.add(n);
        boolean [] newAva2 = new boolean[4];
        for (int k = 0; k < newAva2.length; ++k)
            newAva2[k] = true;
        visited.put(n, newAva2);

        int [][] dst = new int [maze.length][];
        for (int i = 0; i < dst.length; ++i) {
            dst[i] = new int [maze.length];
            for (int j = 0; j < dst[i].length; ++j) {
                dst[i][j] = Integer.MAX_VALUE;
            }
        }

        dst[start[0]][start[1]] = 0;
        int min_possible;
        if (start[0] == dest[0])
            min_possible = Math.abs(start[1] - dest[1]);
        else if (start[1] == dest[1])
            min_possible = Math.abs(start[0] - dest[0]);
        else
            min_possible = Math.abs(start[0] - dest[0]) + Math.abs(dest[1] - start[1]) - 1;

        while (!q.isEmpty()) {
            Node cur = q.removeFirst();
            boolean ava[] = visited.get(cur);
            for (int i = 0; i < directions.length; ++i) {
                if (ava[i]) {
                    int next_x = cur.x + directions[i][0];
                    int next_y = cur.y + directions[i][1];

                    ava[i] = false;

                    int steps = 0;
                    while (isEmptySpace(maze, next_x, next_y)) {
                        //continue the directions
//                        if (((next_x == dest[0]) && (next_y == dest[1]))) {
//                            //add a crazy check here
//                            return 0;
//                        }

                        next_x += directions[i][0];
                        next_y += directions[i][1];
                        ++steps;
                    }
                    next_x -= directions[i][0];
                    next_y -= directions[i][1];

                    if (next_x != cur.x || next_y != cur.y ) {
                        Node newNode = new Node(next_x, next_y);
                        if (!visited.containsKey(newNode)) {
                            boolean [] newAva = new boolean[4];
                            for (int k = 0; k < newAva.length; ++k)
                                newAva[k] = true;

                            newAva[3 - i] = false;
                            visited.put(newNode, newAva);
                            q.add(newNode);
                        } else {
                            boolean [] newAva = visited.get(newNode);
                            newAva[3 - i] = false;
                        }
                    }
                    if (dst[next_x][next_y] > steps + dst[cur.x][cur.y]) {
                        dst[next_x][next_y] = steps + dst[cur.x][cur.y];
                        if (dst[dest[0]][dest[1]] == min_possible)
                            return min_possible;
                    }
                }
            }
        }
        if (dst[dest[0]][dest[1]] == Integer.MAX_VALUE)
            return -1;
        else
            return dst[dest[0]][dest[1]];
    }

//    public int shortestDistanceDij(int[][] maze, int[] start, int[] dest) {
//        if (start[0] == dest[0] && start[1] == dest[1])
//            return 0;
//
//        Map<Node, boolean []> visited = new HashMap<>();
//        Deque<Node> q = new LinkedList<>();
//        Node n = new Node(start[0], start[1]);
//        q.add(n);
//        boolean [] newAva2 = new boolean[4];
//        for (int k = 0; k < newAva2.length; ++k)
//            newAva2[k] = true;
//        visited.put(n, newAva2);
//
//        int [][] dst = new int [maze.length][];
//        for (int i = 0; i < dst.length; ++i) {
//            dst[i] = new int [maze.length];
//            for (int j = 0; j < dst[i].length; ++j) {
//                dst[i][j] = Integer.MAX_VALUE;
//            }
//        }
//
//        dst[start[0]][start[1]] = 0;
//        PriorityQueue<int []> pq = new PriorityQueue<>((a, b)->a[2] - b[2]);
//
//        while (!q.isEmpty()) {
//            Node cur = q.removeFirst();
//            boolean ava[] = visited.get(cur);
//            for (int i = 0; i < directions.length; ++i) {
//                if (ava[i]) {
//                    int next_x = cur.x + directions[i][0];
//                    int next_y = cur.y + directions[i][1];
//
//                    ava[i] = false;
//
//                    int steps = 0;
//                    while (isEmptySpace(maze, next_x, next_y)) {
//                        //continue the directions
////                        if (((next_x == dest[0]) && (next_y == dest[1]))) {
////                            //add a crazy check here
////                            return 0;
////                        }
//
//                        next_x += directions[i][0];
//                        next_y += directions[i][1];
//                        ++steps;
//                    }
//                    next_x -= directions[i][0];
//                    next_y -= directions[i][1];
//
//                    if (next_x != cur.x || next_y != cur.y ) {
//                        Node newNode = new Node(next_x, next_y);
//                        if (!visited.containsKey(newNode)) {
//                            boolean [] newAva = new boolean[4];
//                            for (int k = 0; k < newAva.length; ++k)
//                                newAva[k] = true;
//
//                            newAva[3 - i] = false;
//                            visited.put(newNode, newAva);
//                            q.add(newNode);
//                        } else {
//                            boolean [] newAva = visited.get(newNode);
//                            newAva[3 - i] = false;
//                        }
//                    }
//                    if (dst[next_x][next_y] > steps + dst[cur.x][cur.y]) {
//                        dst[next_x][next_y] = steps + dst[cur.x][cur.y];
//                        if (dst[dest[0]][dest[1]] == min_possible)
//                            return min_possible;
//                    }
//                }
//            }
//        }
//        if (dst[dest[0]][dest[1]] == Integer.MAX_VALUE)
//            return -1;
//        else
//            return dst[dest[0]][dest[1]];
//    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [][] maze = new int [][] {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0},
        };
        int [] start = new int [] {0,4};
        int [] dest = new int [] {3,2};
        System.out.println(s.shortestDistance(maze, start, dest));
    }
}
