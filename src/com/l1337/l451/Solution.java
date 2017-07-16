package com.l1337.l451;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public String frequencySort(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            int old = counter.getOrDefault(s.charAt(i), 0);
            counter.put(s.charAt(i), old + 1);
        }

        TreeMap<Integer, List<Character>> tree = new TreeMap<>();
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            List<Character> l = tree.getOrDefault(entry.getValue(), new ArrayList<>());
            l.add(entry.getKey());
            tree.put(entry.getValue(), l);
        }

        char [] sb = new char[s.length()];
        int k = 0;
        while (!tree.isEmpty()) {
            Map.Entry<Integer, List<Character>> entry = tree.lastEntry();
            List<Character> l = entry.getValue();
            for (int i = 0; i < l.size(); ++i) {
                int value = entry.getKey();
                while (value > 0) {
                    sb[k++] = l.get(i);
                    --value;
                }
            }
            tree.remove(entry.getKey());
        }

        return new String(sb);
    }

//    https://leetcode.com/problems/sort-characters-by-frequency/discuss/93404/C++-O(n)-solution-without-sort()
//    https://leetcode.com/problems/sort-characters-by-frequency/discuss/93420/Java-O(n)-Bucket-Sort-Excel-O(nlogn)-PriorityQueue-Excel-easy-to-understand
    //Don't forget a normal data structure for this purposes...
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.frequencySort("Aabb"));
    }
}
