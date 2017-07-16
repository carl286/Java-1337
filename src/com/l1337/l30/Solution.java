package com.l1337.l30;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    // https://leetcode.com/submissions/detail/63662018/
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        if (words.length == 0 || words[0].length() == 0)
            return ret;
        Map<String, Integer> orgDict = new HashMap<>();
        for (String w : words) {
            orgDict.put(w, 1 + orgDict.getOrDefault(w, 0));
        }

        int unitLength = words[0].length();
        int totalLength = words.length * words[0].length();

        for (int i = 0; i <= s.length() - totalLength; ++i) {
            Map<String, Integer> internalDict = new HashMap<>();
            //check point
            //i, i + u, i + 2u, i + (words.length - 1)*u
            int k;
            for (k = 0; k < words.length; ++k) {
                String tmp = new String(s.substring(i + k * unitLength, i + (k + 1)* unitLength));
                int orgCount = orgDict.getOrDefault(tmp, 0);
                if (orgCount > 0) {
                    internalDict.put(tmp, 1 + internalDict.getOrDefault(tmp, 0));
                    int val = internalDict.get(tmp);
                    if (val > orgCount)
                        break;
                } else {
                    break;
                }
            }
            if (k == words.length) {
                ret.add(i);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String ss = "barfoothefoobarman";
        String [] words = {"foo","bar"};
        System.out.println(s.findSubstring(ss, words));
    }
}
