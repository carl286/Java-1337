package com.l1337.l953;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < order.length(); ++i)
            map.put(order.charAt(i), i);
        for(int i = 0; i < words.length - 1; ++i)
        {
            //compare between i && i + 1
            int j;
            for(j = 0; j < words[i].length() && j < words[i+1].length(); ++j)
            {
                char c1 = words[i].charAt(j), c2 = words[i+1].charAt(j);
                if (map.get(c1) > map.get(c2))
                {
                    return false;
                }
                else if (map.get(c1) < map.get(c2))
                {
                    break;
                }
            }

            if (j == words[i+1].length() && j < words[i].length())
                return false;
        }

        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        String[] words = new String[]{"hello","leetcode"};
//        String order = new String("hlabcdefgijkmnopqrstuvwxyz");

        String[] words = new String[]{"apple","app"};
        String order = new String("abcdefghijklmnopqrstuvwxyz");
        System.out.println(s.isAlienSorted(words, order));
    }
}
