package com.l1337.l317;

//	Shortest Distance from All Buildings, 317
//You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
//	Each 0 marks an empty land which you can pass by freely.
//	Each 1 marks a building which you cannot pass through.
//	Each 2 marks an obstacle which you cannot pass through.
//	For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
//
//			1 - 0 - 2 - 0 - 1
//			|   |   |   |   |
//			0 - 0 - 0 - 0 - 0
//			|   |   |   |   |
//			0 - 0 - 1 - 0 - 0
//	The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
//
//	Note:
//	There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Solution {

    private static final int FREE = 0;
    private static final int BUILDING = 1;
    private static final int OBSTACLE = 2;


    //Please let at previous posts and serach online. Below code will be extremely slow when lots of entry are there.......
//    http://www.cnblogs.com/EdwardLiu/p/5094091.html
//    http://buttercola.blogspot.com/2016/01/leetcode-shortest-distance-from-all.html
    int shortestDistanceHelper(int [][]grid,int i, int j,HashSet<Integer> set) {
        int ret = 0;
        int acc = 0;
        LinkedList<Integer> list = new LinkedList<>();
        list.add(i*grid[0].length+j);
        int [][] directions = {{-1,0}, {+1,0}, {0,-1}, {0,+1}};
        HashSet<Integer> visited = new HashSet<>();
        visited.add(i*grid[0].length+j);

        while (set.size() > 0 && list.size() > 0) {
            ++acc;

            LinkedList<Integer> next = new LinkedList<>();
            while (set.size() > 0 && list.size() > 0) {
                Integer cur = list.removeFirst();
                i = cur/grid[0].length;
                j = cur%grid[0].length;

                for (int k = 0; k < directions.length; ++k) {
                    int u = i + directions[k][0];
                    int v = j + directions[k][1];
                    if (u >= 0 && u < grid.length && v >= 0 && v < grid[0].length) {
                        int key = u * grid[0].length + v;
                        if (!visited.contains(key)) {
                            if (grid[u][v] == FREE) {
                                visited.add(key);
                                next.add(key);
                            } else if (grid[u][v] == BUILDING) {
                                visited.add(key);
                                set.remove(key);
                                ret += acc;
                            }
                        }
                    }
                }

            }
            list = next;
        }

        return set.size() == 0 ? ret : -1;
    }
    public int shortestDistance(int[][] grid) {
        //BFS
        int ret = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == BUILDING) {
                    //Construct visit set
                    set.add(i* (grid[i].length) + j);
                }
            }

        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == FREE) {
                    //explore
                    int local = shortestDistanceHelper(grid,i,j,new HashSet<>(set));
                    if (local > 0)
                        ret = Math.min(ret, local);
                }
            }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();

        int [][] grid = new int [3][];
        for (int i = 0; i < grid.length; ++i)
            grid[i] = new int [5];
        grid[0][0] = BUILDING;
        grid[0][4] = BUILDING;
        grid[2][2] = BUILDING;
        grid[0][2] = OBSTACLE;

//        int [][] grid = new int [2][];
//        for (int i = 0; i < grid.length; ++i)
//            grid[i] = new int [3];
//        grid[0][0]=BUILDING;
//        grid[0][2]=BUILDING;
//        grid[1][1]=BUILDING;

        System.out.println(s.shortestDistance(grid));
    }
}
