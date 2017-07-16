package com.l1337.l139;


import com.leetcode.Trie;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution {

    class TrieNode {
        TrieNode [] next = new TrieNode[26];
        boolean ending = false;
    }

    TrieNode buildTrie(List<String> wordDict)
    {
        TrieNode root = new TrieNode();
        for (String word: wordDict) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); ++i)
            {
                if (cur.next[word.charAt(i) - 'a'] == null)
                {
                    cur.next[word.charAt(i) - 'a'] = new TrieNode();
                }
                cur = cur.next[word.charAt(i) - 'a'];
            }
            cur.ending = true;
        }
        return root;
    }

    boolean dfs(String s, int index, TrieNode node)
    {
        if (index >= s.length())
            return true;

        TrieNode cur = node;
        for (int i = index; i < s.length(); ++i)
        {
            if (cur.next[s.charAt(i) - 'a'] != null)
            {
                cur = cur.next[s.charAt(i) - 'a'];
                if (cur.ending && dfs(s, i + 1, node))
                {
                    return true;
                }
            }
            else
            {
                break;
            }
        }

        return false;
    }

    //why DFS too slow????too many duplicated problems....
    public boolean wordBreakDfs(String s, List<String> wordDict) {
        Set<Character> set2 = s.chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());
        Set<Character> set1 = new HashSet<>();
        for (String s2 : wordDict) {
            set1.addAll(s2.chars()
                    .mapToObj(e->(char)e).collect(Collectors.toSet()));
        }
        if (!set1.containsAll(set2))
            return false;
        //Set<Character> set1 = wordDict.stream().map(d -> (d.chars().mapToObj(c -> (Character)c).collect(Collectors.toSet())));
        return dfs(s, 0, buildTrie(wordDict));
    }

    //still too much duplicate problems here because memo is 1 mentional..., we would need a 2 dimension memo here which is essentially a DP solution...
    boolean dfsWithMemo(String s, int index, TrieNode node, int [] memo)
    {
        if (memo[index] != 0)
            return memo[index] == 1; //0, not init, 1 -> true, 2 --> false
        if (index >= s.length())
        {
            memo[index] = 1;
            return memo[index] == 1;
        }

        TrieNode cur = node;
        for (int i = index; i < s.length(); ++i)
        {
            if (cur.next[s.charAt(i) - 'a'] != null)
            {
                cur = cur.next[s.charAt(i) - 'a'];
                if (cur.ending && dfs(s, i + 1, node))
                {
                    memo[index] = 1;
                    return memo[index] == 1;
                }
            }
            else
            {
                break;
            }
        }

        memo[index] = 2;
        return memo[index] == 1;
    }

    public boolean wordBreakDfsWithMemo(String s, List<String> wordDict) {
        return dfsWithMemo(s, 0, buildTrie(wordDict), new int [s.length() + 1]);
    }

    //for existence check, we don't have to go to all sub-problems....
    public boolean wordBreak2D(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean [][] map = new boolean[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; --i)
        {
            for (int j = i; j < s.length(); ++j)
            {
                //[i, j]
                for(int k = i; k <= j && !map[i][j]; ++k)
                {
                    if (set.contains(s.substring(i, k + 1)) && ((k >= j) || map[k+1][j]))
                    {
                        map[i][j] = true;
                    }
                }
            }
        }
        return map[0][s.length()-1];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //1D
        Set<String> set = new HashSet<>(wordDict);
        boolean [] map = new boolean[s.length()];

        for (int i = s.length() - 1; i >= 0; --i)
        {
            //from i to the end
            for (int j = i; j < s.length() && !map[i]; ++j)
            {
                //j till s.length - 1
                if (set.contains(s.substring(i, j + 1 )) &&( j+1 == s.length() ||map[j+1]))
                {
                    map[i] = true;
                }
            }
        }
        return map[0];
    }

    public List<String> wordBreakIINotOptimized(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        List<List<List<Integer>>> map = new ArrayList<>();
        for(int i = 0; i < s.length(); ++i)
        {
            map.add(new ArrayList<>());
        }

        for (int i = s.length() - 1; i >= 0; --i)
        {
            for (int j = i; j < s.length() - 1; ++j)
            {
                if (set.contains(s.substring(i, j + 1)) && map.get(j + 1).size() > 0)
                {
                    for (int k = 0; k < map.get(j+1).size(); ++k)
                    {
                        /**
                         * This approach would case Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
                         * if we need cache too many internal results. Then the question is how to reduce internal results set...???
                         * "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"
                         */
                        List<Integer> tmp = new ArrayList<>(map.get(j + 1).get(k));
                        tmp.add(i);
                        map.get(i).add(tmp);
                    }
                }
            }
            if (set.contains(s.substring(i)))
            {
                map.get(i).add(new ArrayList<>(Arrays.asList(i)));
            }
        }

        List<String> ret = new ArrayList<>();
        //construct the results
        for (int i = 0; i < map.get(0).size(); ++i)
        {
            List<String> tmp = new ArrayList<>();
            int ending = s.length();
            for (int k = 0; k < map.get(0).get(i).size(); ++k)
            {
                tmp.add(s.substring(map.get(0).get(i).get(k), ending));
                ending = map.get(0).get(i).get(k);
            }
            Collections.reverse(tmp);
            ret.add(String.join(" ", tmp));
        }
        return ret;
    }

    private void wordBreakIIHelper(List<String> ret, String s, List<String> tmp, List<List<Integer>> map, int index)
    {
        if (index == map.size())
        {
            ret.add(String.join(" ", tmp));
            return;
        }

        for (int i = 0; i < map.get(index).size(); ++i)
        {
            String tmps = s.substring(index, map.get(index).get(i));
            tmp.add(tmps);
            wordBreakIIHelper(ret, s, tmp, map, map.get(index).get(i));
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<String> wordBreakII(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        List<List<Integer>> map = new ArrayList<>();
        for(int i = 0; i < s.length(); ++i)
        {
            //each one will have a limit entries, (at most N)
            map.add(new ArrayList<>());
        }
        //may be we should at a dummy ending...
        // map.add(new ArrayList<>());

        for (int i = s.length() - 1; i >= 0; --i)
        {
            for (int j = i + 1; j < s.length(); ++j)
            {
                if (set.contains(s.substring(i, j)) && map.get(j).size() > 0)
                {
                    map.get(i).add(j);
                }
            }
            if (set.contains(s.substring(i)))
            {
                map.get(i).add(s.length());
            }
        }

        List<String> ret = new ArrayList<>();
        wordBreakIIHelper(ret, s, new ArrayList<String>(), map, 0);
        //backstraces....

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        //System.out.println(s.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
        //System.out.println(s.wordBreak("ab", Arrays.asList("a", "b")));
        String ss = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
//        String ss = "abc";
//        List<String> wordDict = Arrays.asList("ab", "b", "c", "a");
//        String ss = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        for (String rs:s.wordBreakII(ss, wordDict)) {
            System.out.println(rs);
        };
    }
}
