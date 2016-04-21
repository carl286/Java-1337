package com.l1337.l302;


//	Smallest Rectangle Enclosing Black Pixels, 302
//An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
// The black pixels are connected, i.e., there is only one black region.
// Pixels are connected horizontally and vertically.
// Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
//	For example, given the following image:
//			[
//			"0010",
//			"0110",
//			"0100"
//			]
//	and x = 0, y = 2,
//	Return 6.

import java.util.HashSet;
import java.util.LinkedList;

public class Solution {

    private int getKey(int x, int y, char[][] image) {
        return x*image[0].length+y;
    }
    int getR(int key, char[][] image) {
        return key/image[0].length;
    }
    int getC(int key, char[][] image) {
        return key%image[0].length;
    }

    //They want an answer that's better than BFS
    public int minArea(char[][] image, int x, int y) {
        //assume image is valid, x,y is on black, all black points are connected..

        LinkedList<Integer> l = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        int [][] directions = {{-1,0},{+1,0},{0,-1},{0,+1}};
        //init
        int key = getKey(x,y,image);
        l.add(key);
        set.add(key);
        int minR = x, maxR=x, minC=y, maxC=y;

        //Do it by BFS????
        while (!l.isEmpty()) {
            int cur = l.removeFirst();
            x = getR(cur,image);
            y = getC(cur,image);

            minR = Math.min(minR,x);
            maxR = Math.max(maxR,x);
            minC = Math.min(minC,y);
            maxC = Math.max(maxC,y);

            for (int i = 0; i < directions.length; ++i) {
                int next_x = x + directions[i][0];
                int next_y = y + directions[i][1];
                if (next_x >= 0 && next_y >= 0 && next_x < image.length && next_y < image[0].length && image[next_x][next_y] == '1') {
                    int newKey = getKey(next_x,next_y,image);
                    if (!set.contains(newKey)) {
                        set.add(newKey);
                        l.add(newKey);
                    }
                }
            }

        }

//        System.out.println(minR);
//        System.out.println(maxR);
//        System.out.println(minC);
//        System.out.println(maxC);
        return (maxR-minR+1)*(maxC-minC+1);
    }



    //	Theorem
//	If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.
//	This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.
//	To find the left boundary, do the binary search in the [0, y) range and find the first column vector who has any black pixel.
//	To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)
//	We can do the same for the other boundaries. The area is then calculated by the boundaries. Thus the algorithm runs in O(m log n + n log m)
//	https://leetcode.com/discuss/85146/java-binary-search-o-nlogm-mlogn-runtime
    //Check col/row manually...

//    https://leetcode.com/discuss/71898/1ms-java-binary-search-dfs-is-4ms



    public static void main(String [] args) {
        Solution s = new Solution();
        char [][] image = {{'0','0','1','0'}, {'0','1','1','0'}, {'0','1','0','0'}};
        int x = 0, y = 2;
        System.out.println(s.minArea(image,x,y));
    }
}
