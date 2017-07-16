package com.l1337.l1320;


import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private Map<Character, int []> map = Stream.of(
            new AbstractMap.SimpleEntry<>('A', new int [] {0,0}),
            new AbstractMap.SimpleEntry<>('B', new int [] {0,1}),
            new AbstractMap.SimpleEntry<>('C', new int [] {0,2}),
            new AbstractMap.SimpleEntry<>('D', new int [] {0,3}),
            new AbstractMap.SimpleEntry<>('E', new int [] {0,4}),
            new AbstractMap.SimpleEntry<>('F', new int [] {0,5}),
            new AbstractMap.SimpleEntry<>('G', new int [] {1,0}),
            new AbstractMap.SimpleEntry<>('H', new int [] {1,1}),
            new AbstractMap.SimpleEntry<>('I', new int [] {1,2}),
            new AbstractMap.SimpleEntry<>('J', new int [] {1,3}),
            new AbstractMap.SimpleEntry<>('K', new int [] {1,4}),
            new AbstractMap.SimpleEntry<>('L', new int [] {1,5}),
            new AbstractMap.SimpleEntry<>('M', new int [] {2,0}),
            new AbstractMap.SimpleEntry<>('N', new int [] {2,1}),
            new AbstractMap.SimpleEntry<>('O', new int [] {2,2}),
            new AbstractMap.SimpleEntry<>('P', new int [] {2,3}),
            new AbstractMap.SimpleEntry<>('Q', new int [] {2,4}),
            new AbstractMap.SimpleEntry<>('R', new int [] {2,5}),
            new AbstractMap.SimpleEntry<>('S', new int [] {3,0}),
            new AbstractMap.SimpleEntry<>('T', new int [] {3,1}),
            new AbstractMap.SimpleEntry<>('U', new int [] {3,2}),
            new AbstractMap.SimpleEntry<>('V', new int [] {3,3}),
            new AbstractMap.SimpleEntry<>('W', new int [] {3,4}),
            new AbstractMap.SimpleEntry<>('X', new int [] {3,5}),
            new AbstractMap.SimpleEntry<>('Y', new int [] {4,0}),
            new AbstractMap.SimpleEntry<>('Z', new int [] {4,1}))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    private int getCost(int [] px, int [] py)
    {
        if (px[0] < 0 || px[1] < 0)
            return 0;
        else
            return Math.abs(px[0]-py[0]) + Math.abs(px[1]-py[1]);
    }

//    https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/discuss/483782/Detailed-explanation-on-optimizing-a-3D2D-DP-to-1D
//    https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/discuss/477652/JavaC%2B%2BPython-1D-DP-O(1)-Space
    public int minimumDistance(String word) {
        int n = word.length();
        if (n <= 0)
            return 0;

        int [][] cost = new int [n+1][n+1];
        for(int i = 0; i < n; ++i)
        {
            for(int j = i + 1; j < n; ++j)
            {
                int dif = getCost(map.get(word.charAt(i)),map.get(word.charAt(j)));
                cost[i+1][j+1] = dif;
//                cost[j][i] = dif;
            }
        }

        int [][][] dp = new int [n+1][n+1][n+1];

        //a valid pos i,j,k  i < k && j < k
        for(int l = 1; l <= n; ++l)
        {
            for(int i = 0; i < l; ++i)
            {
                for(int j = i+1; j < l; ++j)
                {
                   //we won't have i,i,l, (i,j,l) should have the same value as (j,i,l)
                    dp[i][j][l] = Math.min(dp[i+1][j][l] + cost[i][i+1], dp[i][j+1][l] + cost[j][j+1]);
                    dp[j][i][l] = dp[i][j][l];
                }
            }
        }

        return -1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
