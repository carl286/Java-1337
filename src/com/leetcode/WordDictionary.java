package com.leetcode;

/**
 * Created by Ke.Liu on 5/20/16.
 */

//https://leetcode.com/problems/add-and-search-word-data-structure-design/
//211. Add and Search Word - Data structure design
public class WordDictionary {
    // Adds a word into the data structure.
    private TrieNode root;

    WordDictionary() {
        root = new TrieNode();
    }
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.array[word.charAt(i) - 'a'] == null)
                cur.array[word.charAt(i) - 'a'] = new TrieNode();
            cur = cur.array[word.charAt(i) - 'a'];
        }
        cur.ending = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.

    private boolean search(TrieNode cur, String word, int index) {
        if (cur == null)
            return false;
        if (index >= word.length())
            return cur.ending == true;
        if (word.charAt(index) != '.') {
            return search(cur.array[word.charAt(index) - 'a'], word, index + 1);
        } else {
            for (int i = 0; i < 26; i++)
                if (search(cur.array[i], word, index + 1))
                    return true;
        }
        return false;
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    public static void main(String [] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("at");
        dict.addWord("and");
        dict.addWord("an");
        dict.addWord("add");
        dict.search("a");
        dict.search(".at");
        dict.addWord("bat");
        dict.search(".at");
        dict.search("an.");
        dict.search("a.d.");
        dict.search("b.");
        dict.search("a.d");
        dict.search(".");
    }
}
