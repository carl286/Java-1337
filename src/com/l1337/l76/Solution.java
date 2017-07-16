package com.l1337.l76;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";

        Map<Character, Integer> target = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            target.put(t.charAt(i), 1 + target.getOrDefault(t.charAt(i), 0));
        }

        Map<Character, Integer> cur = new HashMap<>();
        int cnt = 0, curIndex = 0, minIndex = -1, minLength = s.length() + 1;
        for (int i = 0; i < s.length(); ++i) {
            if (target.getOrDefault(s.charAt(i), 0) > cur.getOrDefault(s.charAt(i), 0)) {
                ++cnt;
            }

            cur.put(s.charAt(i), 1 + cur.getOrDefault(s.charAt(i), 0));

            while (cnt == t.length()) {
                int curLength = i + 1 - curIndex;
                if (curLength < minLength) {
                    minLength = curLength;
                    minIndex = curIndex;
                }
                cur.put(s.charAt(curIndex), cur.get(s.charAt(curIndex)) - 1);
                if (cur.get(s.charAt(curIndex)) < target.getOrDefault(s.charAt(curIndex), 0))
                {
                    --cnt;
                }
                ++curIndex;
            }
        }

        if (minIndex >= 0)
            return s.substring(minIndex, minIndex + minLength);
        return "";
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String S = "a", T = "a";
        System.out.println(s.minWindow(S, T));
    }
}
