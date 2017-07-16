package com.l1337.l522;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a,b)->b.length() - a.length());
        Set<String> visited = new HashSet<>();

        int i = 0;
        while (i < strs.length) {
            int j = i;
            while (j < strs.length && strs[i].length() == strs[j].length()) {
                ++j;
            }

            Map<String, Integer> candidate = new HashMap<>();

            for (int k = i; k < j; ++k)
                candidate.put(strs[k], candidate.getOrDefault(strs[k], 0) + 1);

            for (int k = i; k < j; ++k) {
                if (candidate.get(strs[k]) == 1) {
                    //check potentials
                    boolean found = true;
                    Iterator<String> iter = visited.iterator();
                    while (iter.hasNext()) {
                        if (isASubSequence(strs[k],iter.next())) {
                            found = false;
                            break;
                        }
                    }
                    if (found)
                        return strs[k].length();
                } else {
                    //let's put it to next rounds
                }
            }

            if (strs[i].length() == 1)
                break;

            for (String key : candidate.keySet()) {
                visited.add(key);
            }

            i = j;
        }
        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findLUSlength(new String [] {"aaa", "aa", "aaa"}));
    }
}
