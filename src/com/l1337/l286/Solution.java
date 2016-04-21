package com.l1337.l286;

//	Walls and Gates, 286
//	You are given a m x n 2D grid initialized with these three possible values.
//	-1 - A wall or an obstacle.
//	0 - A gate.
//	INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than2147483647.
//	Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//	For example, given the 2D grid:
//	INF  -1  0  INF
//	INF INF INF  -1
//	INF  -1 INF  -1
//	0  -1 INF INF
//	After running your function, the 2D grid should be:
//			3  -1   0   1
//			2   2   1  -1
//			1  -1   2  -1
//			0  -1   3   4

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class Solution {

    private static int OBSTACLE = -1;
    private static int GATE = 0;
    private static int EMPTY = Integer.MAX_VALUE;
    private static int [][] directions = {{-1,0}, {1,0},{0,-1},{0,1}};


    int getKey(int i, int j, int[][] rooms) {
        return i*rooms[0].length + j;
    }
    int getX(int key, int[][] rooms) {
        return key / rooms[0].length;
    }
    int getY(int key, int[][] rooms) {
        return key % rooms[0].length;
    }
    public void wallsAndGates(int[][] rooms) {

        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms.length; ++j) {
                if (rooms[i][j] == GATE) {
                    //Do BFS
                    LinkedList<Integer> l = new LinkedList<>();
                    l.add(getKey(i,j,rooms));
                    HashSet<Integer> set = new HashSet<>();

                    int level = 1;
                    while (!l.isEmpty()) {
                        LinkedList<Integer> next = new LinkedList<>();
                        while (!l.isEmpty()) {
                            int cur = l.removeFirst();
                            int x = getX(cur, rooms);
                            int y = getY(cur, rooms);

                            //get four next
                            for (int k = 0; k < directions.length; ++k) {
                                int nx = x + directions[k][0];
                                int ny = y + directions[k][1];
//                                if (nx >= 0 && ny >= 0 && nx < rooms.length && ny < rooms[0].length && !set.contains(getKey(nx,ny,rooms)) && rooms[nx][ny] != OBSTACLE && rooms[nx][ny] != GATE && rooms[nx][ny] > level) {
//                                if (nx >= 0 && ny >= 0 && nx < rooms.length && ny < rooms[0].length && rooms[nx][ny] != OBSTACLE && rooms[nx][ny] != GATE && rooms[nx][ny] > level) {
                                if (nx >= 0 && ny >= 0 && nx < rooms.length && ny < rooms[0].length && rooms[nx][ny] > level) {
                                    rooms[nx][ny] = level;
                                    next.add(getKey(nx,ny,rooms));
//                                    set.add(getKey(nx,ny,rooms));
                                }
                            }

                        }
                        l = next;
                        ++level;
                    }
                }
            }
        }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int[][] rooms = new int [4][];
        for (int i = 0; i < rooms.length; ++i) {
            rooms[i] = new int [4];
            Arrays.fill(rooms[i], EMPTY);
        }
     /*
        //	INF  -1  0  INF
//	INF INF INF  -1
//	INF  -1 INF  -1
//	0  -1 INF INF
         */
        rooms[0][1] = OBSTACLE;
        rooms[0][2] = GATE;
        rooms[1][3] = OBSTACLE;
        rooms[2][1] = OBSTACLE;
        rooms[2][3] = OBSTACLE;
        rooms[3][0] = GATE;
        rooms[3][1] = OBSTACLE;

        s.wallsAndGates(rooms);
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[0].length; ++j)
                System.out.print(rooms[i][j] + "\t");
            System.out.println();
        }
    }
}


//	https://leetcode.com/discuss/60149/straightforward-python-solution-without-recursion