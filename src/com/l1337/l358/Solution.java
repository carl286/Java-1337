package com.l1337.l358;

import java.util.*;

/*
Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.
Example 2:
str = "aaabc", k = 3

Answer: ""

It is not possible to rearrange the string.
Example 3:
str = "aaadbbcc", k = 2

Answer: "abacabcd"

Another possible answer is: "abcabcda"

The same letters are at least distance 2 from each other.
 */


//The problem itself is not that complicacted
//But it involes lots of code...
//http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-become-at-least-d-distance-away/
class ChNode {
    char val;
    int cnt;
    ChNode(char val) {
        this.val = val;
        this.cnt = 1;
    }
}


//http://www.cnblogs.com/grandyang/p/5586009.html
//https://leetcode.com/discuss/108174/c-unordered_map-priority_queue-solution-using-cache
//http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-become-at-least-d-distance-away/
public class Solution {

    //How to make the code to distribute them a bit more elegantly????
    public String rearrangeString(String str, int k) {
        //first counter
        HashMap<Character, ChNode> map = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            if (!map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), new ChNode(str.charAt(i)));
            } else {
                ChNode node = map.get(str.charAt(i));
                ++node.cnt;
            }
        }
        PriorityQueue<ChNode> pq = new PriorityQueue<>(new Comparator<ChNode>() {
            public int compare(ChNode n1, ChNode n2) {
                return Integer.compare(n2.cnt, n1.cnt);
            }
        });
        for (ChNode node : map.values())
            pq.add(node);

        char [] tmp = new char[str.length()];
        int length = str.length();
        int i = 0;
        while (length > 0) {
            int size = Math.min(k, length);
            List<ChNode> local = new ArrayList<>();

            for (int j = 0; j < size; ++j) {
                if (pq.isEmpty())
                    return "";
                ChNode cur = pq.poll();
                tmp[i++] = cur.val;
                --cur.cnt;
                if (cur.cnt > 0)
                    local.add(cur);
                --length;
            }

            if (local.size() > 0) {
                for (ChNode n : local)
                    pq.add(n);
            }
        }

        return new String(tmp);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        String str = "aabbcc";
//        int k = 3;
//        String str = "aaabc";
//        int k = 3;
//        String str = "aaadbbcc";
//        int k = 2;
        String str = "aaabc";
        int k = 2;
        System.out.println(s.rearrangeString(str,k));
    }
}
