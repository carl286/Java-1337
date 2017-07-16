package com.l1337.l433;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private static final char [] ATGCS = {'A', 'T', 'G', 'C'};

//    https://leetcode.com/problems/minimum-genetic-mutation/description/
    public int minMutation(String start, String end, String[] bank) {
        Set<String> banks = new HashSet<String>();
        for (String s : bank)
            banks.add(s);
        if (!banks.contains(end))
            return -1;

        Set<String> visited = new HashSet<>();
        visited.add(start);
        List<String> queue = new ArrayList<>();
        queue.add(start);

        int depth = 0;
        while (!queue.isEmpty()) {
            List<String> nextQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); ++i) {
                StringBuilder cur = new StringBuilder(queue.get(i));
                for (int k = 0; k < cur.length(); ++k) {
                    char c = cur.charAt(k);
                    for (int m = 0; m < ATGCS.length; ++m) {
                        if (ATGCS[m] != c) {
                            cur.setCharAt(k, ATGCS[m]);
                            String candidate = new String(cur);
                            if (banks.contains(candidate) && !visited.contains(candidate)) {
                                if (candidate.equals(end))
                                    return 1 + depth;
                                nextQueue.add(candidate);
                            }
                        }
                    }
                    cur.setCharAt(k, c);
                }
            }
            queue = nextQueue;
            ++depth;
        }

        //BFS
        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String [] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

        System.out.println(s.minMutation(start, end, bank));
    }
}
