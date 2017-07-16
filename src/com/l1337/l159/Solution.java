package com.l1337.l159;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    /*
    Longest Substring with At Most Two Distinct Characters
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
For example, Given s = “eceba”,
T is "ece" which its length is 3.

Input: “eceba”
Output: 3
Explanation:
T is "ece" which its length is 3.

Input: “aaa”
Output: 3
    * */

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int ret = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int start = 0, i = 0; i < s.length(); ++i)
        {
            if (map.containsKey(s.charAt(i)) || map.size() < 2)
            {
                map.put(s.charAt(i), 1 + map.getOrDefault(s.charAt(i), 0));
            }
            else
            {
                //pop out
                while (map.size() > 1)
                {
                    map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                    if (map.get(s.charAt(start)) == 0)
                        map.remove(s.charAt(start));
                    ++start;
                    map.put(s.charAt(i), 1);
                }
            }
            ret = Math.max(ret, i - start + 1);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("aaaaaaaa"));
    }
}
