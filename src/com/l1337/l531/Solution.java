package com.l1337.l531;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    /*
    Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:

Input:
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.


Note:

The range of width and height of the input 2D array is [1,500].

     */

    int findLonelyPixel(char[][] picture) {

        //just BF, nothing interesting..
//        http://massivealgorithms.blogspot.com/2017/08/leetcode-531-lonely-pixel-i.html

        return 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
