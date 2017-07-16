package com.l1337.l733;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution {

    private final int [][] directiosn = new int [][]{{-1,0},{1,0},{0,1},{0,-1}};
    private boolean isValid(int i, int j,  int[][] board) {
        return i >= 0 && i < board.length && j >= 0 && j < board[i].length;
    }

    private int getHash(int i, int j, int [][] board) {
        return i * board[0].length + j;
    }

    private int[] getCoordinates(int code, int [][] board) {
        return new int[]{code / board[0].length, code % board[0].length};
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int sourceCorlor = image[sr][sc];
        if (sourceCorlor != newColor) {
            LinkedList<Integer> list = new LinkedList<>();
            Set<Integer> set = new HashSet<>();

            set.add(getHash(sr, sc, image));
            list.add(getHash(sr, sc, image));

            while (!list.isEmpty()) {
                Integer cur = list.removeFirst();
                int [] coor = getCoordinates(cur, image);
//                System.out.println(coor[0] + "\t" + coor[1] + "\t" + cur);
                image[coor[0]][coor[1]] = newColor;

                for (int i = 0; i < directiosn.length; ++i) {
                    int x = coor[0] + directiosn[i][0];
                    int y = coor[1] + directiosn[i][1];
                    int code = getHash(x,y,image);
//                    System.out.println("Adding:" + x + "\t" + y + "\t" + code);
                    if (isValid(x,y,image) && image[x][y] == sourceCorlor && !set.contains(code)) {
                        list.add(code);
                        set.add(code);
                    }
                }
            }
        }

        return image;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.floodFill(new int [][]{{0,0,0}, {0,0,0}}, 0,0,2));
    }
}
