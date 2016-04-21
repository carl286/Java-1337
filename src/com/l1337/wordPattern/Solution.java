package com.l1337.wordPattern;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {


//    290. Word Pattern
//    https://leetcode.com/problems/word-pattern/
public boolean wordPattern(String pattern, String str) {
    String [] strs = str.split("\\s+");
    for (String s : strs)
        System.out.println(s);

    if (pattern.length() != strs.length)
        return false;
    //both empty?
    HashMap<Character, String> map1 = new HashMap<>();
    HashMap<String, Character> map2 = new HashMap<>();
    for (int i = 0; i < pattern.length(); ++i) {
        if (map1.containsKey(pattern.charAt(i))) {
            //FOR GOD SAKE, I made the same mistake here every time.....
            if (!map1.get(pattern.charAt(i)).equals(strs[i]))
                return false;
        } else  {
            if (map2.containsKey(strs[i]))
                return false;
            map1.put(pattern.charAt(i), strs[i]);
            map2.put(strs[i], pattern.charAt(i));

        }
    }

    return true;
}


    //	Word Pattern II, 291
//Given a pattern and a string str, find if str follows the same pattern.
//	Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
//	Examples:
//	pattern = "abab", str = "redblueredblue" should return true. (a->red, b->blue)
//	pattern = "aaaa", str = "asdasdasdasd" should return true. (a->asd)
//	pattern = "aabb", str = "xyzabcxzyabc" should return false.
//	Notes: You may assume both pattern and str contains only lowercase letters.

    //Below is a good one for explaining the problems..
    //https://leetcode.com/discuss/63252/share-my-java-backtracking-solution

    boolean wordPatternMatch(String pattern, int i, String str, int j, HashMap<Character, String> map, Set<String> set) {
        if (i >= pattern.length())
            return j == str.length();
        if (j >= str.length())
//            return i == pattern.length();
            return false;

        //find one to match i
        //Below code is stupid, see above POST for references...
        for (int k = j; k < str.length(); ++k) {
//            System.out.println(j + "\t" + (k + 1));
            String tmp = str.substring(j, k + 1);
            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(tmp)) {
                    return false;
                } else {
                    if (wordPatternMatch(pattern, i + 1, str, k + 1, map, set))
                        return true;
                }
            } else if (set.contains(tmp))
                continue;
            else {
                map.put(pattern.charAt(i), tmp);
                set.add(tmp);
                if (wordPatternMatch(pattern, i + 1, str, k + 1, map, set))
                    return true;
                set.remove(tmp);
                map.remove(pattern.charAt(i));
            }
        }

        return false;
    }
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.length() == 0)
            return str.length() == 0;

        HashMap<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return wordPatternMatch(pattern, 0, str, 0, map, set);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        String pattern = "abba", str = "dog cat cat dog";
//        System.out.println(s.wordPattern(pattern, str));

//        String pattern = "abab";
//        String str = "redblueredblue";
        //	pattern = "aaaa", str = "asdasdasdasd" should return true. (a->asd)
//	pattern = "aabb", str = "xyzabcxzyabc" should return false.

        String pattern = "aba";
        String str = "aaaa";

        System.out.println(s.wordPatternMatch(pattern, str));
    }
}
