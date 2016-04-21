package com.l1337.l318;

//318. Maximum Product of Word Lengths
//https://leetcode.com/problems/maximum-product-of-word-lengths/

import java.util.HashMap;

public class Solution {

    int getWordMask(String s) {
        int mask = 0;
        for (int i = 0; i < s.length(); ++i)
            mask |= 1 << (s.charAt(i)-'a');
        return mask;
    }
    public int maxProduct(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; ++i)
            map.put(words[i], getWordMask(words[i]));

        int ret = 0;
        for (int i = 0; i < words.length; ++i)
            for (int j = i + 1; j < words.length; ++j)
                if ((map.get(words[i]) & map.get(words[j])) == 0)
                    ret = Math.max(ret, words[i].length() * words[j].length());
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String [] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(s.maxProduct(words));
    }
}
