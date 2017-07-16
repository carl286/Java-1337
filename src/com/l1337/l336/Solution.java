package com.l1337.l336;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//https://leetcode.com/problems/palindrome-pairs/
//336. Palindrome Pairs
public class Solution {

    public boolean isPalindrome(String s) {
        if (s.length() <= 1)
            return true;

        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                ++i;
                --j;
            } else {
                return false;
            }
        }

        return true;
    }

//    https://leetcode.com/submissions/detail/56859802/
//    https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
//    https://leetcode.com/problems/palindrome-pairs/discuss/79210/The-Easy-to-unserstand-JAVA-Solution
    public List<List<Integer>> palindromePairs(String[] words) {
        //Below Code TLE, this is a tricky question...
        //assume no empty string????, non-null....
        /*
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                if (isPalindrome(words[i] + words[j])) {
                    ret.add(Arrays.asList(i,j));
                }
                if (isPalindrome(words[j] + words[i])) {
                    ret.add(Arrays.asList(i,j));
                }
            }
        }
        return ret;
        */
        List<List<Integer>> ret = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        //no duplicate in string
        for (int i = 0; i < words.length; ++i)
            map.put(words[i], i);

        //Please take some times to think about those combos..
        for (int i = 0; i < words.length; ++i) {
            //???How to handle empty string????
            //so that we skip "" string
            if (words[i].equals(""))
                continue;
            //whole string should be treated specially.
            if (isPalindrome(words[i])) {
                if (map.containsKey("")) {
                    ret.add(Arrays.asList(i, map.get("")));
                    ret.add(Arrays.asList(map.get(""), i));
                }
            } else {
                String tmp = new StringBuilder(words[i].substring(0, words[i].length())).reverse().toString();
                if (map.containsKey(tmp) && map.get(tmp) != i) {
                    ret.add(Arrays.asList(map.get(tmp), i));
                }
            }

            for (int j = 1; j < words[i].length(); ++j) {
                if (isPalindrome(words[i].substring(0, j))) {
                    String tmp = new StringBuilder(words[i].substring(j, words[i].length())).reverse().toString();
                    if (map.containsKey(tmp)) {
                        ret.add(Arrays.asList(map.get(tmp), i));
                    }
                }
                if (isPalindrome(words[i].substring(j, words[i].length()))) {
                    String tmp = new StringBuilder(words[i].substring(0, j)).reverse().toString();
                    if (map.containsKey(tmp)) {
                        ret.add(Arrays.asList(i, map.get(tmp)));
                    }
                }
            }
        }
        return ret;
    }


    public List<List<Integer>> palindromePairs_March4_21_TLE(String[] words) {
        //correctness is right below, but got TLE...
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < words.length; ++i)
            for (int j = i + 1; j < words.length; ++j)
            {
                //check i, j and j,i
                String tmp =words[i] + words[j];
                if (isPalindrome(tmp))
                    ret.add(Arrays.asList(i,j));
                tmp = words[j] + words[i];
                if (isPalindrome(tmp))
                    ret.add(Arrays.asList(j,i));
            }
        return ret;
    }

    public List<List<Integer>> palindromePairs_March4_21(String[] words) {
        Pair<String, Integer>[] original = new Pair [words.length];
        Pair<String, Integer>[] backwards = new Pair [words.length];
        for (int i = 0; i < words.length; ++i)
        {
            original[i] = new Pair<>(words[i], i);
            backwards[i] = new Pair<>(new String(new StringBuilder(words[i]).reverse()), i);
        }
        Arrays.sort(original, (a,b) -> (a.getKey().compareTo(b.getKey())));
        Arrays.sort(backwards, (a,b) -> (a.getKey().compareTo(b.getKey())));


        //correctness is right below, but got TLE...
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < words.length; ++i)
            for (int j = i + 1; j < words.length; ++j)
            {
                //check i, j and j,i
                String tmp =words[i] + words[j];
                if (isPalindrome(tmp))
                    ret.add(Arrays.asList(i,j));
                tmp = words[j] + words[i];
                if (isPalindrome(tmp))
                    ret.add(Arrays.asList(j,i));
            }
        return ret;
    }
































    public static void main(String [] args) {
//        String [] words = {"abcd","dcba","lls","s","sssll"};
//        String [] words = {"abcd","dcba"};
//        String [] words = {"a","b","c","ab","ac","aa"};
        String [] words = {"a", "ab", "aa", "aaaa"};
        Solution s = new Solution();
        System.out.println(s.palindromePairs(words));
    }
}
