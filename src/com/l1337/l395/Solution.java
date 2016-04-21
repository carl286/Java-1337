package com.l1337.l395;


import java.util.*;

public class Solution {

//    https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/submissions/
    public int longestSubstring(String s, int k) {
        if (k <= 0 || s.length() <= 0)
            return s.length();
        if (k == 1)
            return s.length();
        if (k > s.length())
            return 0;

        //k >= 2 below && s.length() >= k
        int ret = 0;

        /*
        List<Integer> [] map = (List<Integer>[]) new List [26];
        for (int i = 0; i < map.length; ++i)
            map[i] = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map[c-'a'].add(i);
        }

        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((l1, l2) -> l1.get(0) - l2.get(0));

        for (int i = 0; i < map.length; i++) {
            if (map[i].size() >= k)
                pq.add(map[i]);
        }

        while (!pq.isEmpty()) {
            int [] cnts = new int [26];

            int start = pq.peek().get(0) - 1;
            int last = start;
            while (!pq.isEmpty() && (pq.peek().get(0) == last + 1)) {
                ++last;
                ++cnts[s.charAt(last) - 'a'];
                List<Integer> top = pq.poll();
                top.remove(0);
                if (!top.isEmpty())
                    pq.add(top);
            }

            //prune the sequences....
            while (start < last) {
                int index = s.charAt(start+1) - 'a';
                int cnt = cnts[index];
                if (cnt > 0 && cnt < k) {
                    --cnts[index];
                }
            }
            int i = 0;
            while (i < cnts.length && (cnts[i] == 0 || cnts[i] >= k))
                ++i;
            if (i == cnts.length)
                ret = Math.max(last - start, ret);
        }
        */

        //not a good way to go...
        for (int i = 0; i < s.length() && (ret <s.length() - i); ++i) {
            int [] cnts = new int [26];
//            boolean flag = true;
            for (int j = i; j < s.length(); ++j) {
                ++cnts[s.charAt(j) - 'a'];
                if (cnts[s.charAt(j) - 'a'] >= k) {
                    int m = 0;
                    while (m < cnts.length && (cnts[m] == 0 || cnts[m] >= k))
                        ++m;
                    if (m == cnts.length)
                        ret = Math.max(j - i + 1, ret);
                }
            }
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        String ss = "ababbc";
//        int k = 2;
        String ss = "abbbbbbcaa";
        int k = 3;
        System.out.println(s.longestSubstring(ss, k));
    }
}
