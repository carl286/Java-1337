package com.l1337.l864;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/shortest-path-to-get-all-keys/discuss/146878/Java-BFS-Solution
    private char EMPTY_CELL = '.';
    private char WALL = '#';
    private char STARTING_POINT = '@';
    private Set<Character> KEYS = new HashSet<>(Arrays.asList('a', 'b'));
    private Set<Character> LOCKS = new HashSet<>(Arrays.asList('A', 'B'));
    public int shortestPathAllKeys(String[] grid) {
        int keys = 0, start_x = -1, start_y = -1;
        int m = grid.length, n = grid[0].length();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
            {
                char c = grid[i].charAt(j);
                if (c == STARTING_POINT)
                {
                    start_x = i;
                    start_y = j;
                }
                else if (c >= 'a' && c <= 'f')
                {
                    keys++;
                }
            }

        return 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
