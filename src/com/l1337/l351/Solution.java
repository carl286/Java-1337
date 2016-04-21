package com.l1337.l351;

//351	Android Unlock Patterns
/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9,
count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

        Rules for a valid pattern:

        Each pattern must connect at least m keys and at most n keys.
        All the keys must be distinct.
        If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
        The order of keys used matters.

        Explanation:

        | 1 | 2 | 3 |
        | 4 | 5 | 6 |
        | 7 | 8 | 9 |


        Invalid move: 4 - 1 - 3 - 6
        Line 1 - 3 passes through key 2 which had not been selected in the pattern.

        Invalid move: 4 - 1 - 9 - 2
        Line 1 - 9 passes through key 5 which had not been selected in the pattern.

        Valid move: 2 - 4 - 1 - 3 - 6
        Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

        Valid move: 6 - 5 - 4 - 1 - 9 - 2
        Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

        Example:
        Given m = 1, n = 1, return 9.
*/

import java.util.HashSet;

public class Solution {
    int [][] direct = {{},//0
                        {2,4,5},//1
                        {1,3,4,5,6},//2
                        {2,5,6},//3
                        {1,2,5,7,8},//4
                        {1,2,3,4,6,7,8,9},//5
                        {2,3,5,8,9},//6
                        {4,5,8},//7
                        {7,4,5,6,9},//8
                        {5,6,8},//9
                        };
    int [][][] indirect = {{},//0
                            {{3,2}, {7,4}, {9,5}},//1
                            {{8,5}},//2
                            {{1,2}, {7,5}, {9,6}},//3
                            {{6,5}},//4
                            {},//5
                            {{4,5}},//6
                            {{1,4}, {3,5}, {9,8}},//7
                            {{2,5}},//8
                            {{3,6}, {7,8}, {1,5}},//9
                        };
    int total;
    void visit(int m, int n, HashSet<Integer> visited, int toVisit) {
        visited.add(toVisit);
        if (visited.size() >= m && visited.size() <= n) {
            for (Integer i : visited)
                System.out.print(i + "\t");
            System.out.println();
            ++total;
        }
        if (visited.size() >= n) {
            visited.remove(toVisit);
            return;
        }

        //continue to expand
        //find direct acceess
        for (int i = 0; i < direct[toVisit].length; ++i) {
            if (!visited.contains(direct[toVisit][i])) {
                visit(m,n,visited,direct[toVisit][i]);
            }
        }
        for (int i = 0; i < indirect[toVisit].length; ++i) {
            if (!visited.contains(indirect[toVisit][i][0]) && visited.contains(indirect[toVisit][i][1])) {
                visit(m,n,visited,indirect[toVisit][i][0]);
            }
        }

        visited.remove(toVisit);
    }
    int numberOfPatterns(int m, int n) {
//      assume  1 ≤ m ≤ n ≤ 9
        HashSet<Integer> visited = new HashSet<>();
        total = 0;
        //You can use symmetric here, 1,3,7,9 are the same, 2,4,6,8 are the same....
        for (int i = 1; i <= 9; ++i)
            visit(m,n,visited, i);
        return total;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int m = 3, n = 3;
        System.out.println(s.numberOfPatterns(m,n));
    }
}
