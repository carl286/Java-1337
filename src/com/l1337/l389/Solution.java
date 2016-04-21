package com.l1337.l389;


import java.util.HashMap;

public class Solution {

    public char findTheDifference(String s, String t) {
        String shortter, longer;
        if (s.length() < t.length()) {
            shortter = s;
            longer = t;
        } else {
            shortter = t;
            longer = s;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : shortter.toCharArray())
            if (map.containsKey(c))
                map.put(c, 1 + map.get(c));
            else
                map.put(c, 1);

        for (char c : longer.toCharArray())
            if (map.containsKey(c)) {
                if (map.get(c) == 1)
                    map.remove(c);
                else
                    map.put(c, map.get(c)-1);
            } else {
                return c;
            }
        return ' ';
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findTheDifference("abcd", "abcde"));
    }
}
