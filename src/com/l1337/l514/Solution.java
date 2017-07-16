package com.l1337.l514;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

//    dfs TLE...
    private int dfs(String ring, int cur, String key, int depth, Map<Character, List<Integer>> map) {
        if (depth >= key.length())
            return 0;

        if (ring.charAt(cur) == key.charAt(depth))
            return dfs(ring, cur, key, depth + 1, map);

        List<Integer> indexes = map.get(key.charAt(depth));
        if (indexes == null)
            return -1;

        int ret = -1;
        for (int i = 0; i < indexes.size(); ++i) {
            int next_index = indexes.get(i);
            int cost = Math.min(Math.abs(next_index - cur), ring.length() - Math.abs(next_index - cur));
            int remaining_cost = dfs(ring, next_index, key, depth + 1, map);
            if (remaining_cost >= 0 && (ret == -1 || remaining_cost + cost< ret))
                ret = remaining_cost + cost;
        }

        return ret;
    }

    public int findRotateSteps(String ring, String key) {
        //ch to indexes
        Map<Character, List<Integer>> ch2IndexMap = new HashMap<>();
        for (int i = 0; i < ring.length(); ++i) {
            Character c = ring.charAt(i);
            List<Integer> cur = ch2IndexMap.getOrDefault(c, new ArrayList<>());
            cur.add(i);
            ch2IndexMap.put(c, cur);
        }

        int cost = dfs(ring , 0, key, 0, ch2IndexMap);

        if (cost < 0)
            return -1;
        else
            return cost + key.length();
    }

    private int dfsWithMemo(String ring, int cur, String key, int depth, Map<Character, List<Integer>> map, int [][] memo) {
        if (depth >= key.length())
            return 0;

        if (memo[cur][depth] >= 0)
            return memo[cur][depth];

        if (ring.charAt(cur) == key.charAt(depth)) {
            memo[cur][depth] = dfsWithMemo(ring, cur, key, depth + 1, map, memo);
            return memo[cur][depth];
        }

        List<Integer> indexes = map.get(key.charAt(depth));
        if (indexes == null)
            return -1;

        int ret = -1;
        for (int i = 0; i < indexes.size(); ++i) {
            int next_index = indexes.get(i);
            int cost = Math.min(Math.abs(next_index - cur), ring.length() - Math.abs(next_index - cur));
            int remaining_cost = dfsWithMemo(ring, next_index, key, depth + 1, map, memo);
            if (remaining_cost >= 0 && (ret == -1 || remaining_cost + cost< ret))
                ret = remaining_cost + cost;
        }

        memo[cur][depth] = ret;
        return ret;
    }

//    https://leetcode.com/submissions/detail/174640252/
    public int findRotateStepsWithMemo(String ring, String key) {
        //ch to indexes
        Map<Character, List<Integer>> ch2IndexMap = new HashMap<>();
        for (int i = 0; i < ring.length(); ++i) {
            Character c = ring.charAt(i);
            List<Integer> cur = ch2IndexMap.getOrDefault(c, new ArrayList<>());
            cur.add(i);
            ch2IndexMap.put(c, cur);
        }

        int [][] memo = new int [ring.length()][];
        for (int i = 0; i < memo.length; ++i) {
            memo[i] = new int [key.length()];
            Arrays.fill(memo[i], -1);
        }
        int cost = dfsWithMemo(ring , 0, key, 0, ch2IndexMap, memo);

        if (cost < 0)
            return -1;
        else
            return cost + key.length();
    }

//    https://leetcode.com/problems/freedom-trail/discuss/98902/Concise-Java-DP-Solution
    public int findRotateStepsDp(String ring, String key) {
        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }

        return dp[0][0] + m;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String ring = "godding", key = "gd";

        System.out.println(s.findRotateStepsWithMemo(ring, key));
    }
}
