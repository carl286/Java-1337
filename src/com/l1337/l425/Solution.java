package com.l1337.l425;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
    /**
     * Given a set of words (without duplicates), find all word squares you can build from them.

     A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

     For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

     b a l l
     a r e a
     l e a d
     l a d y
     Note:

     There are at least 1 and at most 1000 words.
     All words will have the exact same length.
     Word length is at least 1 and at most 5.
     Each word contains only lowercase English alphabet a-z.


     Example 1:

     Input:
     ["area","lead","wall","lady","ball"]

     Output:
     [
     [ "wall",
     "area",
     "lead",
     "lady"
     ],
     [ "ball",
     "area",
     "lead",
     "lady"
     ]
     ]

     Explanation:
     The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).


     Example 2:

     Input:
     ["abat","baba","atan","atal"]

     Output:
     [
     [ "baba",
     "abat",
     "baba",
     "atan"
     ],
     [ "baba",
     "abat",
     "baba",
     "atal"
     ]
     ]

     Explanation:
     The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
     */

//    http://www.cnblogs.com/grandyang/p/6006000.html
// or build trie, do the same
    void wordSquaresSetHelper(int level, char [][] templates, Map<String, List<String>> mapper, Set<String> set, List<List<String>> ret) {
        if (level == templates.length) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < templates.length; ++i)
                list.add(new String(templates[i]));
            ret.add(list);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < level; ++k)
            sb.append(templates[k][level]);
        String prefix = sb.toString();
        if (mapper.containsKey(prefix)) {
            for (String string : mapper.get(prefix)) {
//                if(!set.contains(string)) {
                if(true) {
                    set.add(string);
                    for (int i = 0; i < string.length(); ++i)
                        templates[level][i] = string.charAt(i);
                    wordSquaresSetHelper(level + 1, templates, mapper, set, ret);
                    set.remove(string);
                }
            }
        }

    }

    public List<List<String>> wordSquaresSet(String[] words) {
        //Build mapper
        Map<String, List<String>> mapper = new HashMap<>();
        for (String w : words) {
            for (int i = 0; i < w.length(); ++i) {
                String tmp = w.substring(0, i + 1);
                if (!mapper.containsKey(tmp))
                    mapper.put(tmp, new ArrayList<>());
                mapper.get(tmp).add(w);
            }
        }

        int length = words[0].length();
        List<List<String>> ret = new ArrayList<>();
        Set<String> set = new HashSet<>();

        char [][] templates = new char [length][];
        for (int i = 0; i < templates.length; ++i)
            templates[i] = new char [length];

        for (String w : words) {
            for (int i = 0; i < w.length(); ++i)
                templates[0][i] = w.charAt(i);
            set.add(w);
            wordSquaresSetHelper(1, templates, mapper, set, ret);
            set.remove(w);
        }
        return ret;
    }

    public List<List<String>> wordSquares(String[] words) {
        return wordSquaresSet(words);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String[] words = {"abat","baba","atan","atal"};
//        String[] words = {"cad", "abc", "baa"};
        for (List<String> l : s.wordSquares(words)) {
            for (String w : l) {
                System.out.println(w);
            }
            System.out.println("********");
        }
    }
}
