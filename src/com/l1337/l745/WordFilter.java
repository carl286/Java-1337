package com.l1337.l745;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class WordFilter {

    private final String [] words;
    private final Map<String, TreeSet<Integer>> prefix;
    private final Map<String, TreeSet<Integer>> posfix;

    public WordFilter(String[] words) {
        this.words = words;
        this.prefix = new HashMap<>();
        this.posfix = new HashMap<>();

        for (int i = 0; i < words.length; ++i) {
            for (int j = 0; j < words[i].length(); ++j) {
                TreeSet<Integer> prefixSet = prefix.getOrDefault(words[i].substring(0,j+1), new TreeSet<>());
                TreeSet<Integer> posfixSet = posfix.getOrDefault(words[i].substring(j), new TreeSet<>());

                prefixSet.add(i);
                posfixSet.add(i);

                prefix.put(words[i].substring(0,j+1), prefixSet);
                posfix.put(words[i].substring(j), posfixSet);
            }
        }
    }

    public int f(String prefix, String suffix) {
        if (prefix.equals("")) {
            if (prefix.equals(suffix))
                return words.length - 1;
            else {
                TreeSet<Integer> posfixSet = this.posfix.get(suffix);
                if (posfixSet == null)
                    return -1;
                else
                    return posfixSet.last();
            }
        } else if (suffix.equals("")) {
            TreeSet<Integer> prefixSet = this.prefix.get(prefix);
            if (prefixSet == null)
                return -1;
            else
                return prefixSet.last();
        } else {
            TreeSet<Integer> posfixSet = this.posfix.get(suffix);
            TreeSet<Integer> prefixSet = this.prefix.get(prefix);
            if (posfixSet == null || prefixSet == null)
                return -1;

            TreeSet<Integer> tmp = new TreeSet<>(posfixSet);
            tmp.retainAll(prefixSet);
            if (tmp.size() > 0) {
                return tmp.last();
            } else {
                return -1;
            }
        }
    }

//    https://leetcode.com/problems/prefix-and-suffix-search/discuss/110051/527-ms-Java-Solution-with-Detailed-Explanation
//    https://leetcode.com/problems/prefix-and-suffix-search/discuss/110044/Three-ways-to-solve-this-problem-in-Java
    public static void main(String [] args) {
//        WordFilter s = new WordFilter(new String [] {/*"abbbababbb",*/
//                "baaabbabbb",
////                "abababbaaa",
////                "abbbbbbbba",
////                "bbbaabbbaa",
////                "ababbaabaa",
////                "baaaaabbbb",
////                "babbabbabb",
//                "ababaababb",
//                /*"bbabbababa" */});
//        System.out.println(s.f("baaabba","b"));

        WordFilter s = new WordFilter(new String [] { "pop"});
        System.out.println(s.f("p",""));
    }
}
