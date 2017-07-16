package com.l1337.l211;


public class WordDictionary {

    class Trie
    {
        Trie [] data = new Trie[26];
        boolean ending = false;
    }
    /** Initialize your data structure here. */
    Trie root;
    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        Trie cur = root;
        for(int i = 0; i < word.length(); ++i)
        {
            int index = word.charAt(i) - 'a';
            if (cur.data[index] == null)
                cur.data[index] = new Trie();
            cur = cur.data[index];
        }
        cur.ending = true;
    }

    private boolean search(Trie root, String word, int i) {
        if (root == null)
            return false;
        if (i >= word.length())
            return root.ending;
        if (word.charAt(i) == '.')
        {
            for(int k = 0; k < root.data.length; ++k)
            {
                if (search(root.data[k], word, i+1))
                    return true;
            }
            return false;
        }
        else
        {
            int index = word.charAt(i) - 'a';
            return search(root.data[index], word, i+1);
        }
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    public static void main(String [] args) {
        WordDictionary s = new WordDictionary();
        s.addWord("bad");
        s.addWord("dad");
        s.addWord("mad");

        System.out.println(s.search("pad"));
        System.out.println(s.search("bad"));
        System.out.println(s.search(".ad"));
        System.out.println(s.search("b.."));
    }
}
