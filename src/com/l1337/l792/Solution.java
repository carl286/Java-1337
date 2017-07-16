package com.l1337.l792;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    private boolean isSubsequence(String s, String a)
    {
        int i = 0, j = 0;
        while ( i < s.length() && j < a.length())
        {
            if (s.charAt(i) == a.charAt(j))
                ++j;
            ++i;
        }

        return j >= a.length();
    }
    public int numMatchingSubseq(String s, String[] words) {
        int ret = 0;
        Map<String, Boolean> cache = new HashMap<>();
        for(int i = 0; i < words.length; ++i)
        {
            if (!cache.containsKey(words[i]))
            {
                cache.put(words[i], isSubsequence(s, words[i]));
            }
            if (cache.get(words[i]))
                ++ret;
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
