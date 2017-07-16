package com.l1337.l1197;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    /*
    Minimum Knight Moves
    In an infinite chess board with coordinates from -infinity to +infinity,
    you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:

|x| + |y| <= 300
     */
    int minKnightMovesMarch10_21(int x, int y) {
        return 0;
    }

    int minKnightMoves(int x, int y) {
        int [][] directions = new int[][]{{2,1}, {1,2}, {-1,2}, {-2,2},{-2,-1}, {-1,-2}, {1,-2}, {2,-1}};
        Pair<Integer, Integer> start = new Pair<>(0,0);

        Pair<Integer, Integer> end = new Pair<>(x, y);
        int steps = 0;
        if (start.equals(end))
            return steps;

        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        List<Pair<Integer, Integer>> dq = new ArrayList<>();
        visited.add(start);
        dq.add(start);

        while (!dq.isEmpty())
        {
            ++steps;
            List<Pair<Integer, Integer>> nextdq = new ArrayList<>();
            for(int i = 0; i < dq.size(); ++i)
            {
                for (int j = 0; j < directions.length; ++j)
                {
                    Pair<Integer, Integer> nextNode = new Pair<>(dq.get(i).getKey() + directions[j][0], dq.get(i).getValue() + directions[j][1]);
                    if (!visited.contains(nextNode))
                    {
                        if (nextNode.equals(end))
                            return steps;
                        nextdq.add(nextNode);
                        visited.add(nextNode);
                    }
                }
            }

            dq = nextdq;
        }
        return -1;
    }

//    http://lixinchengdu.github.io/algorithmbook/leetcode/minimum-knight-moves.html
    /*
    How to optimize it to reduce search space..??
     */
    public static void main(String [] args) {
        Solution s = new Solution();
        //System.out.println(s.minKnightMoves(2,0));
        System.out.println(new Pair<Integer, Integer>(1,1).equals(new Pair<Integer, Integer>(1,1)));
    }
}
