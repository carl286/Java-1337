package com.l1337.l527;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    /*
    Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:

Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]


Note:

Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
     */

    //assume input has unique sets
    //To have a better performance, you can do the string calculation as the last steps....

//    http://www.cnblogs.com/grandyang/p/6818742.html
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, List<String>> potential = new HashMap<>();
        Map<String, String> finalSet = new HashMap<>();

        for (int i = 0; i < dict.size(); ++i) {
            String cur = dict.get(i);
            if (cur.length() <= 3) {
                //no need to waste time to put int potetial
                finalSet.put(cur, cur);
            } else {
                int length = cur.length();
                String tmp = cur.charAt(0) + Integer.toString(length - 2) + cur.charAt(length - 1);
                if (cur.length() <= tmp.length()) {
                    finalSet.put(cur, cur);
                } else {
                    if (potential.containsKey(tmp)) {
                        //there was a conflicts
                        //get all existing conflicts
                        List<String> list = potential.get(tmp);
                        //how to resolve conflicts
                        //take a peek
                        String first = list.get(0);
                        if (finalSet.get(first).equals(first)) {
                            // no way
                            finalSet.put(cur, cur);
                        } else {
                            // you might need take it out to improve...

                            //get potential starting point
                            int no_conflict_point = 0;//the last point where you don't have conflicts
                            Set<String> conflictSets = new HashSet<>();
                            int string_length = first.length();

                            for (int j = 0; j < list.size(); ++j) {
                                int k = 0;
                                String cur_s = list.get(k);
                                while (k < string_length && cur_s.charAt(k) == cur.charAt(k)) ++k;

                                if (no_conflict_point < k) {
                                    conflictSets.clear();
                                    conflictSets.add(cur_s);
                                    no_conflict_point = k;
                                } else if (no_conflict_point == k) {
                                    conflictSets.add(cur_s);
                                }
                            }

                            //only those in conflict sets needs a recheck
                            ++no_conflict_point;
                            int remaining = string_length - no_conflict_point - 1;
                            if (remaining < 2) {
//                                all use original ones
                                finalSet.put(cur, cur);
                                Iterator<String> iter = conflictSets.iterator();
                                while (iter.hasNext()) {
                                    String conflicts = iter.next();
                                    finalSet.put(conflicts, conflicts);
                                }
                            } else {
                                finalSet.put(cur, cur.substring(0, no_conflict_point) + remaining + cur.charAt(string_length - 1));
                                Iterator<String> iter = conflictSets.iterator();
                                while (iter.hasNext()) {
                                    String conflicts = iter.next();
                                    finalSet.put(conflicts, conflicts.substring(0, no_conflict_point) + remaining + conflicts.charAt(string_length - 1));
                                }
                            }
                        }

                        list.add(cur);
                    } else {
                        List<String> newList = new ArrayList<>();
                        newList.add(cur);
                        potential.put(tmp, newList);
                        finalSet.put(cur, tmp);
                    }
                }
            }
        }

        List<String> ret = new ArrayList<>();
        for (int i = 0; i < dict.size(); ++i)
            ret.add(finalSet.get(dict.get(i)));
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.wordsAbbreviation(Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion")));
        for (String ss : s.wordsAbbreviation(Arrays.asList("like", "god", "internal", "interxal", "interyal", "interzal", "me", "internet", "interval", "intension", "face", "intrusion"))) {
            System.out.print(ss + "\t");
        }
        System.out.println();
    }
}
