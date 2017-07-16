package com.l1337.l438;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        if (s.length() < p.length())
            return ret;

        int [] map = new int [26];
        for (int i = 0; i < p.length(); ++i)
            map[p.charAt(i) - 'a']++;

        int [] mapped = new int [26];
        int start = 0, cur = 0;
        while (cur < s.length()) {
            int index = s.charAt(cur) - 'a';
            mapped[index]++;
            while (map[index] < mapped[index]) {
                mapped[s.charAt(start++) - 'a']--;
            }

            if (cur - start + 1== p.length())
                ret.add(start);
            ++cur;
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        for (int i : s.findAnagrams("abab", "ba"))
            System.out.println(i);
    }
}
