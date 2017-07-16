package com.l1337.l269;


import java.io.StringReader;
import java.util.*;

public class Solution {
    /*
    https://www.cnblogs.com/grandyang/p/5250200.html
    [LeetCode] 269. Alien Dictionary 另类字典

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
     */

    public String alienOrder(String[] words) {
        StringBuilder sb = new StringBuilder();
        Set<Character> characterSet = new HashSet<>();
        Map<Character, Set<Character>> dependency = new HashMap<>();
        Map<Character, Set<Character>> reverseDependency = new HashMap<>();

        for(int i = 0; i < words.length; ++i)
        {
            for(int j = 0; j < words[i].length(); ++j)
                characterSet.add(words[i].charAt(j));

            //for comparison
            if (i > 0)
            {
                for(int k = 0; k < words[i].length() && k < words[i-1].length(); ++k)
                {
                    if (words[i].charAt(k) == words[i-1].charAt(k))
                        continue;
                    else
                    {
                        dependency.putIfAbsent(words[i-1].charAt(k), new HashSet<>());
                        dependency.get(words[i-1].charAt(k)).add(words[i].charAt(k));

                        reverseDependency.putIfAbsent(words[i].charAt(k), new HashSet<>());
                        reverseDependency.get(words[i].charAt(k)).add(words[i-1].charAt(k));
                        break;
                    }
                }
            }
        }

        Set<Character> visited = new HashSet<>();
        Deque<Character> dq = new ArrayDeque<>();
        for(Character c : characterSet)
        {
            //not depends on others
            if (reverseDependency.getOrDefault(c, new HashSet<>()).size() == 0)
            {
                dq.addLast(c);
                visited.add(c);
                sb.append(c);
            }
        }

        while (!dq.isEmpty())
        {
            Character c = dq.pollFirst();

            Set<Character> nextSet = dependency.getOrDefault(c, new HashSet<>());

            for(Character next: nextSet)
            {
                if (!visited.contains(next))
                {
                    Set<Character> depends = reverseDependency.getOrDefault(next, new HashSet<>());
                    depends.remove(c);
                    if (depends.size() == 0)
                    {
                        visited.add(next);
                        dq.addLast(next);
                        sb.append(next);
                    }
                }

            }
        }

        //think about edge cases....
        if (sb.length() != characterSet.size())
            return "Invalid";
        return sb.toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String [] words = new String []
        {
                "z",
                "x",
                "z"
        };
        System.out.println(s.alienOrder(words));
    }
}
