package com.l1337.l720;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/longest-word-in-dictionary/discuss/109118/Java-solution-with-Trie
    public String longestWord(String[] words) {
        Set<String> dict = new HashSet<>();
        Collections.addAll(dict, words);

        Arrays.sort(words, new Comparator<String>() {
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                } else {
                    return o2.length() - o1.length();
                }
            }
        });

        Set<String> visited = new HashSet<>();

        for (int i = 0; i < words.length; ++i) {
            int k = words[i].length();

            while (k > 1) {
                String sub = words[i].substring(0, k);
                if (visited.contains(sub))
                    break;

                visited.add(sub);
                if (dict.contains(sub))
                    --k;
            }

            if (k == 1 && dict.contains(words[i].substring(0,k)))
                return words[i];
        }

        return "";
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
