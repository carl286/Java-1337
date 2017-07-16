package com.l1337.l676;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//https://leetcode.com/problems/implement-magic-dictionary/discuss/107446/Easy-14-lines-Java-solution-HashMap
//https://leetcode.com/problems/implement-magic-dictionary/discuss/107453/Easiest-JAVA-with-Trie-no-need-to-count-the-number-of-changes
//https://leetcode.com/problems/implement-magic-dictionary/discuss/107465/Efficient-Trie-and-Java-8-w-Explanation
public class MagicDictionary {

    private Set<String> data;

    private boolean diffbyOneWord(String s1, String s2) {
        if (s1.length() != s2.length() || s1.length() <= 0)
            return false;
        int i = 0, j = s1.length() - 1;
        while (i < s1.length() && s1.charAt(i) == s2.charAt(i))
            ++i;
        while (j > i  && s1.charAt(j) == s2.charAt(j)) {
            --j;
        }

        return j == i;
    }
    /** Initialize your data structure here. */
    public MagicDictionary() {
        data = new HashSet<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict)
            data.add(word);
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        //??
        //let'say you have a,b and I am query b, should I return succ or false??
        Iterator<String> iter = data.iterator();
        while (iter.hasNext()) {
            if (diffbyOneWord(iter.next(), word))
                return true;
        }

        return false;
    }

    public static void main(String [] args) {
        MagicDictionary s = new MagicDictionary();
        s.buildDict(new String [] {"hello", "leetcode"});
        System.out.println(s.search("hello"));
        System.out.println(s.search("hhllo"));
        System.out.println(s.search("hell"));
        System.out.println(s.search("leetcoded"));
        System.out.println(s.search("leetcote"));
    }
}
