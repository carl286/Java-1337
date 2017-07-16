package com.l1337.l49;


import java.util.*;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (int i  = 0; i < strs.length; ++i) {
            char [] tmp = strs[i].toCharArray();
            Arrays.sort(tmp);
            String key = new String(tmp);
            List<String> val = map.getOrDefault(key, new ArrayList<>());
            val.add(strs[i]);
            map.put(key, val);
        }

        for (String key : map.keySet()) {
            ret.add(map.get(key));
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String [] list = {"eat","tea","tan","ate","nat","bat"};
        s.groupAnagrams(list);
    }
}
