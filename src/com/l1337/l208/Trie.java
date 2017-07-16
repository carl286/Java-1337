package com.l1337.l208;


public class Trie {

    class Node
    {
        boolean ending = false;
        Node [] array = new Node [26];
    }

    private Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i)
        {
            if (cur.array[word.charAt(i) - 'a'] == null)
                cur.array[word.charAt(i) - 'a'] = new Node();
            cur = cur.array[word.charAt(i) - 'a'];
        }
        cur.ending = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); ++i)
        {
            if (cur.array[word.charAt(i) - 'a'] != null)
                cur = cur.array[word.charAt(i) - 'a'];
            else
                return false;
        }

        return cur.ending;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); ++i)
        {
            if (cur.array[prefix.charAt(i) - 'a'] != null)
                cur = cur.array[prefix.charAt(i) - 'a'];
            else
                return false;
        }

        return true;
    }

    public static void main(String [] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // returns true
        trie.search("app");     // returns false
        trie.startsWith("app"); // returns true
        trie.insert("app");
        trie.search("app");     // returns true
    }
}
