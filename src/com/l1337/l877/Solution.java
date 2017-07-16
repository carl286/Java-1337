package com.l1337.l877;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/stone-game/

//    https://leetcode.com/problems/stone-game/discuss/154610/DP-or-Just-return-true
//    https://leetcode.com/problems/stone-game/discuss/154660/Java-This-is-minimax-%2B-dp-(fully-detailed-explanation-%2B-generalization-%2B-easy-understand-code)
//    https://leetcode.com/problems/stone-game/discuss/261718/Step-by-Step-Recursive-TopDown-BottomUp-and-BottomUp-using-O(n)-space-in-Java
    public boolean stoneGame(int[] piles) {
        int dp [][] = new int[piles.length][piles.length];

        //do we need this
//        for (int i = 0; i < dp.length; ++i)
//            dp[i][i] = piles[i];

        //init should be l = 2;
        int l = 2;
        for (int i = 0; i + l - 1< piles.length; ++i)
        {
            dp[i][i+l - 1] = Math.max(piles[i], piles[i+l - 1]);
        }

        for (l = 4; l <= piles.length; l += 2)
        {
            for(int i = 0; i + l - 1< piles.length; ++i)
            {
                dp[i][i+l-1] = Math.max(piles[i] + Math.min(dp[i+1][i+l-2], dp[i+2][i+l-1]),
                        piles[i+l-1] + Math.min(dp[i][i+l-3], dp[i+1][i+l-2]));
            }

        }
        int total = 0;
        for (int i = 0; i < piles.length; ++i)
            total += piles[i];

        return dp[0][piles.length-1] > total - dp[0][piles.length-1];
    }

//    https://leetcode.com/problems/stone-game-ii/

//    https://leetcode.com/problems/stone-game-ii/discuss/355710/Java-simple-DP-solution-with-explanation
//    https://leetcode.com/problems/stone-game-ii/discuss/345230/JavaPython-DP-Solution
//    https://leetcode.com/problems/stone-game-ii/discuss/345247/C%2B%2B-DP-(Tabulation)
    public int stoneGameII(int[] piles) {
        //X, i, j
        int [][][] dp = new int[piles.length][piles.length][piles.length];
        //init


        //int m = 1; // map to int [0][i][j];
//        for (int i = 0, m = 1; i < piles.length; ++i)
//        {
//            dp[m-1][i][i] = piles[i];
//            if (i + 1 < piles.length)
//            {
//                dp[m-1][i][i+1] = piles[i] + piles[i+1];
//            }
//        }


            for (int i = piles.length - 1; i >= 0; --i)
            {
                for (int j = i; j < piles.length; ++j)
                {

                    for (int m = 1; m <= piles.length; ++m) {
                        dp[m-1][i][j] = Integer.MIN_VALUE;
                        //how to cover i ... j
                        int acc = 0;
                        for (int k = 0; k < (m << 1) && (i + k) <= j; ++k) {
                            acc += piles[i + k];
                            dp[m - 1][i][j] = Math.max(dp[m - 1][i][j], acc - (i + k + 1 <= j ? dp[Math.max(k, m - 1)][i + k + 1][j] : 0));
                        }
                    }
                }
            }

//            for (int m = 0; m < piles.length; ++m)
//            {
//                System.out.println("m = " + m);
//                for (int i = 0; i < piles.length; ++i)
//                {
//                    System.out.println();
//                    for (int j = 0; j < piles.length; ++j)
//                    {
//                        System.out.print("(m: " + (m + 1) + "\t i: " + (i + 1) + "\t: j:" + (j+ 1) + ")\t" + dp[m][i][j] + "\t");
//                    }
//                    //System.out.println();
//                }
//                System.out.println();
//            }
            int total = 0;
            for (int i = 0; i < piles.length; ++i)
                total += piles[i];
        return (dp[0][0][piles.length-1] + total) >> 1;
    }


//    https://leetcode.com/problems/stone-game-iii/
//    https://leetcode.com/problems/stone-game-iii/discuss/564896/Java-Minimax-and-DP-Bottom-Up-Solutions-Clean-code-O(N)
//    https://leetcode.com/problems/stone-game-iii/discuss/564260/JavaC%2B%2BPython-DP-O(1)-Space
    public String stoneGameIII(int[] stoneValue) {
        int [] dp = new int [stoneValue.length];

        dp[dp.length - 1] = stoneValue[stoneValue.length - 1];
        for (int i = dp.length - 2; i >= 0; --i)
        {
            //we will have at least 1 one value
            dp[i] = stoneValue[i] - dp[i + 1];
            for (int k = 1, acc = stoneValue[i]; k < 3 && i + k < stoneValue.length; ++k)
            {
                acc += stoneValue[i + k];
                if (i + k + 1 < stoneValue.length)
                    dp[i] = Math.max(acc - dp[i + k + 1], dp[i]);
                else
                    dp[i] = Math.max(acc, dp[i]);
            }
        }

        for (int i = 0; i < dp.length; ++i)
            System.out.print(dp[i] + "\t");
        return dp[0] == 0 ? "Tie" : (dp[0] > 0 ? "Alice" : "Bob");
    }

    /*
    1510 Stone Game IV

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile.  On each player’s turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n. Return True if and only if Alice wins the game otherwise return False, assuming both players play optimally.

Example 1:

Input: n = 1
Output: true
Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
Example 2:

Input: n = 2
Output: false
Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
Example 3:

Input: n = 4
Output: true
Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
Example 4:

Input: n = 7
Output: false
Explanation: Alice can't win the game if Bob plays optimally.
If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob removes the last one (7 -> 3 -> 2 -> 1 -> 0).
If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7 -> 6 -> 2 -> 1 -> 0).
Example 5:

Input: n = 17
Output: false
Explanation: Alice can't win the game if Bob plays optimally.
Constraints:

1 <= n <= 10^5
     */

    public boolean winnerSquareGame(int n) {
        //assume n >= 1
        int sq = (int) Math.sqrt(n);
        int tmp [] = new int [sq];
        for (int i = 1; i <= sq; ++i)
            tmp[i-1] = i * i;

        boolean [] dp = new boolean [n + 1];
        //default dp[0] = false;
        for (int i = 1; i < dp.length; ++i)
        {
            //how to tackle i
            for (int k = 0;k < tmp.length && i >= tmp[k] && !dp[i]; ++k)
                dp[i] |= (!dp[i - tmp[k]]);

        }

        return dp[n];
    }

//    https://leetcode.com/problems/stone-game-v/
//    https://leetcode.com/problems/stone-game-v/discuss/911676/Java-O(n3)-O(n2-log-n)-and-O(n2)-with-explanation
//    https://leetcode.com/problems/stone-game-v/discuss/806879/C%2B%2B-O(n2log(n))-solution-and-why-top-down-approach-is-faster
//    https://leetcode.com/problems/stone-game-v/discuss/807330/C%2B%2B-O(N2)-DP
    public int stoneGameV(int[] stoneValue) {
        int [][] dp = new int[stoneValue.length][stoneValue.length];
        for (int i = 1; i < stoneValue.length; ++i)
            stoneValue[i] += stoneValue[i-1];
        for (int l = 2; l <= stoneValue.length; ++l)
        {
            for (int i = stoneValue.length - 1; i >= 0; --i)
            {
                if (i + l -1 < stoneValue.length)
                {
                    dp[i][i+l-1] = Integer.MIN_VALUE;
                    //handle i ... i + l - 1, length is l
                    for (int j = i; j < i + l - 1; ++j)
                    {
                        int leftSum = stoneValue[j] - (i - 1 >= 0 ? stoneValue[i-1] : 0);
                        int rightSUm = stoneValue[i+l-1] - stoneValue[j];
                        if (leftSum < rightSUm)
                        {
                            dp[i][i+l-1] = Math.max(dp[i][i+l-1], leftSum + dp[i][j]);
                        }
                        else if (leftSum > rightSUm)
                        {
                            dp[i][i+l-1] = Math.max(dp[i][i+l-1], rightSUm + dp[j+1][i + l - 1]);
                        }
                        else
                        {
                            dp[i][i+l-1] = Math.max(dp[i][i+l-1], leftSum + Math.max(dp[i][j], dp[j+1][i + l - 1]));
                        }
                    }
                }
            }

//            System.out.println("l: " + l);
//            for (int i = 0; i < stoneValue.length; ++i)
//            {
//                for (int j = 0; j < stoneValue.length; ++j)
//                {
//                    System.out.print("(" + i + "," + j + ")\t" + dp[i][j] + "\t");
//                }
//                System.out.println();
//            }
        }

        return dp[0][stoneValue.length-1];
    }

//    https://leetcode.com/problems/stone-game-vi/
//    https://leetcode.com/problems/stone-game-vi/discuss/969574/JavaC%2B%2BPython-Sort-by-Value-Sum
//    https://leetcode.com/problems/stone-game-vi/discuss/969635/math-proof
//    https://leetcode.com/problems/stone-game-vi/discuss/969817/Strategy-proof
public int stoneGameVI(int[] A, int[] B) {
    int n = A.length;
    int[][] sums = new int[n][];
    for (int i = 0; i < n; i++) {
        sums[i] = new int[] {A[i] + B[i], A[i], B[i]};
    }
    Arrays.sort(sums, (a, b) -> Integer.compare(b[0], a[0]));
    int a = 0;
    int b = 0;
    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
            a += sums[i][1];
        } else {
            b += sums[i][2];
        }
    }
    return Integer.compare(a, b);
}

    //    https://leetcode.com/problems/stone-game-vii/
    public int stoneGameVII(int[] stones) {
        int [][] dp = new int[stones.length][stones.length];
        for (int i = 1; i < stones.length; ++i)
            stones[i] += stones[i-1];

        for (int l = 2; l <= stones.length; ++l)
        {
            //i ... i + l - 1
            for (int i = stones.length - l; i >= 0; --i)
            {
                dp[i][i+l-1] = Math.max(stones[i+l-2] - (i - 1 >= 0 ? stones[i-1] : 0) - dp[i][i+l-2],
                        stones[i+l-1] - stones[i] - dp[i+1][i+l-1]);
            }
        }

        return dp[0][stones.length - 1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        // System.out.println(s.stoneGameII(new int []{5,3,4,5}));
        //System.out.println(s.stoneGameII(new int []{5,3,4,5}));
//        System.out.println(s.stoneGameII(new int []{2,7,9,4,4}));
//        System.out.println(s.stoneGameIII(new int []{-1,-2,-3}));
//        System.out.println(s.stoneGameIII(new int []{-1, -2,-3}));
//        System.out.println(s.stoneGameIII(new int []{1,2,3, -9}));
//        System.out.println(s.stoneGameV(new int []{6,2,3,4,5,5}));
//        System.out.println(s.stoneGameV(new int []{6,2,3}));
        System.out.println(s.stoneGameVII(new int []{7,90,5,1,100,10,10,2}));
//        for(int i = 1; i <= 17; ++i)
//            System.out.println("i: " + i + "\t" + s.winnerSquareGame(i));
    }
}
