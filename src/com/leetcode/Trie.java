package com.leetcode;

class TrieNode {
    // Initialize your data structure here.
    TrieNode [] array;
    boolean ending;
    public TrieNode() {
        array = new TrieNode[26];
        ending = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.array[word.charAt(i) - 'a'] == null)
                cur.array[word.charAt(i) - 'a'] = new TrieNode();
            cur = cur.array[word.charAt(i) - 'a'];
        }
        cur.ending = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.array[word.charAt(i) - 'a'] == null)
                return false;
            cur = cur.array[word.charAt(i) - 'a'];
        }
        return cur.ending == true;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (cur.array[prefix.charAt(i) - 'a'] == null)
                return false;
            cur = cur.array[prefix.charAt(i) - 'a'];
        }
        return true;
    }

    public static void main(String [] args) {
        Trie trie = new Trie();
        trie.insert("key");
        System.out.println(trie.search("key"));
        System.out.println(trie.startsWith("ke"));
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
