package com.l1337.l3;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();

        if (length <= 1)
            return length;

        Set<Character> set = new HashSet<>();
        int ret = 0, start = 0, end = 0;

        while (end < length)
        {
            if (!set.contains(s.charAt(end)))
            {
                set.add(s.charAt(end));
            }
            else
            {
                while (s.charAt(start) != s.charAt(end))
                {
                    set.remove(s.charAt(start));
                    ++start;
                }
                ++start;
            }
            ++end;
            ret = Math.max(ret, end - start);
        }

        return ret;
    }

    /*
    //we can progress more aggressively

        public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
     */
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
