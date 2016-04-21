package com.l1337.l383;

//https://leetcode.com/problems/ransom-note/

import java.util.HashMap;

public class Solution {

    //Do you care about upper/lower cases??? Spaces????
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() <= 0)
            return true;
        if (magazine == null || ransomNote.length() > magazine.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); ++i)
            if (map.containsKey(ransomNote.charAt(i)))
                map.put(ransomNote.charAt(i), 1 + map.get(ransomNote.charAt(i)));
            else
                map.put(ransomNote.charAt(i), 1);

        for (int i = 0; i < magazine.length() && !map.isEmpty(); ++i) {
            Character c = magazine.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) == 1)
                    map.remove(c);
                else
                    map.put(c, map.get(c)-1);
            }
        }

        return map.isEmpty();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
