package com.l1337.l524;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    private boolean isASubSequence(String a, String b) {
        //a.length() <= b.length()
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                ++i;
                ++j;
            } else {
                ++j;
            }
        }

        return i == a.length();
    }

//    https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/discuss/99588/Short-Java-Solutions-Sorting-Dictionary-and-Without-Sorting
//    https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/discuss/99590/Short-Python-solutions
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o2.compareTo(o1);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });

//        if (s.length() <= 0)
//            return "";
        int length = s.length();
        //find the length index by yourself;
        int index;
        int l = -1, r = d.size();
        while (l + 1 != r) {
            int mid = l + (r - l)/2;
            if (d.get(mid).length() <=length) {
                l = mid;
            } else {
                r = mid;
            }
        }

        while (l >= 0 && !isASubSequence(d.get(l), s)) {
            --l;
        }

        if (l >= 0)
            return d.get(l);

        return "";
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findLongestWord("", Arrays.asList("a","b","c")));
    }
}
