package com.l1337.l677;


import java.util.HashMap;
import java.util.Map;

public class MapSum {
    static class TrieNode {
        TrieNode [] children;
        int sum;
        TrieNode() {
            children = new TrieNode[26];
        }
    }

    private Map<String, Integer> map = new HashMap<>();
    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    //only lower case character????
    public void insert(String key, int val) {
        //can we have empty string??? like ""
        //assume no null String as input
        //how long of the input String???
        int dec = 0;
        if (map.containsKey(key)) {
            if (map.get(key) == val)
                return;
            else
                dec = map.get(key);
        }
        map.put(key, val);

        TrieNode cur = root;
        for (int i = 0; i < key.length(); ++i) {
            cur.sum += val;
            cur.sum -= dec;
            if (cur.children[key.charAt(i) - 'a'] == null) {
                cur.children[key.charAt(i) - 'a'] = new TrieNode();
            }
            cur = cur.children[key.charAt(i) - 'a'];
        }

        cur.sum += val;
        cur.sum -= dec;
    }

    //assume no sum overflow might happen
    public int sum(String prefix) {
        //can we have empty string??? like ""
        TrieNode cur = root;
        for (int i = 0; i < prefix.length() && cur != null; ++i) {
            cur = cur.children[prefix.charAt(i) - 'a'];
        }

        return cur == null ? 0 : cur.sum;
    }

    public static void main(String [] args) {
        MapSum s = new MapSum();
        s.insert("apple", 3);
        System.out.println(s.sum("ap"));
        s.insert("app", 2);
        s.insert("apple", 10);
        System.out.println(s.sum("ap"));
    }
}
