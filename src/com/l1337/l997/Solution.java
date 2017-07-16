package com.l1337.l997;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int findJudge(int N, int[][] trust) {
        int ret = -1;
        int degree [] = new int [N+1];
        Set<Integer> candidates = new HashSet<>();
        for (int i = 1; i <= N; ++i)
            candidates.add(i);
        for (int i = 0; i < trust.length; ++i)
        {
            candidates.remove(trust[i][0]);
            ++degree[trust[i][1]];
        }

        for (int i = 1; i <= N; ++i)
        {
            if (degree[i] == N-1 && candidates.contains(i))
            {
                if (ret != -1)
                    return -1;
                ret = i;
            }
        }

        return ret;
    }

//    https://leetcode.com/problems/find-the-town-judge/discuss/242938/JavaC%2B%2BPython-Directed-Graph
//    https://leetcode.com/problems/find-the-town-judge/discuss/242952/C%2B%2B-4-lines-%22Find-the-Celebrity%22
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
