package com.l1337.l726;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    Map<String, Integer> add(Map<String, Integer> map1, Map<String, Integer> map2) {
        List<String> key1 = new ArrayList<>(map1.keySet());
        List<String> key2 = new ArrayList<>(map2.keySet());

        Collections.sort(key1);
        Collections.sort(key2);

        TreeMap<String, Integer> ret = new TreeMap<>();
        int i = 0, j = 0;
        while (i < key1.size() && j < key2.size()) {
            int cmp = key1.get(i).compareTo(key2.get(j));
            if (cmp < 0) {
                ret.put(key1.get(i), map1.get(key1.get(i)));
                ++i;
            } else if (cmp > 0) {
                ret.put(key2.get(j), map2.get(key2.get(j)));
                ++j;
            } else {
                ret.put(key1.get(i), map1.get(key1.get(i)) + map2.get(key2.get(j)));
                ++j;
                ++i;
            }
        }

        while (i < key1.size()) {
            ret.put(key1.get(i), map1.get(key1.get(i)));
            ++i;
        }

        while (j < key2.size()) {
            ret.put(key2.get(j), map2.get(key2.get(j)));
            ++j;
        }

        return ret;
    }
    public String countOfAtoms(String formula) {

        return null;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
