package com.l1337.l490;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Solution {

//    https://leetcode.com/articles/the-maze/

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

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;

        Map<Node, boolean []> visited = new HashMap<>();
        Deque<Node> q = new LinkedList<>();
        Node n = new Node(start[0], start[1]);
        q.add(n);
        boolean [] newAva2 = new boolean[4];
        for (int k = 0; k < newAva2.length; ++k)
            newAva2[k] = true;
        visited.put(n, newAva2);

        while (!q.isEmpty()) {
            Node cur = q.removeFirst();
            boolean ava[] = visited.get(cur);
            for (int i = 0; i < directions.length; ++i) {
                if (ava[i]) {
                    int next_x = cur.x + directions[i][0];
                    int next_y = cur.y + directions[i][1];

                    ava[i] = false;

                    while (isEmptySpace(maze, next_x, next_y)) {
                        //continue the directions
                        if (((next_x == destination[0]) && (next_y == destination[1]))) {
                            return true;
                        }

                        next_x += directions[i][0];
                        next_y += directions[i][1];
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
                }
            }
        }

        return false;
    }

//    http://www.cnblogs.com/grandyang/p/6381458.html
    public static void main(String [] args) {
        Solution s = new Solution();
        int [][] maze = new int [][] {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0},
        };
//        int [][] maze = new int [][] {
//                {0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0},
//                {0, 0, 0, 1, 0},
//                {1, 1, 0, 1, 0},
//                {0, 0, 0, 0, 0},
//        };

        int [] start = new int [] {0,4};
        int [] dest = new int [] {4,4};
//        int [] dest = new int [] {1,0};
        System.out.println(s.hasPath(maze, start, dest));
    }
}
