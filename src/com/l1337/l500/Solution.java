package com.l1337.l500;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private static String [] l = new String[]{"0123456789", "qwertyuiopQWERTYUIOP", "asdfghjklASDFGHJKL", "zxcvbnmZXCVBNM"};
    private Set<Character> [] sets;

    public Solution() {
        sets = new Set[l.length];
        for (int i = 0; i < l.length; ++i) {
            sets[i] = new HashSet<>();
            for (int j = 0; j < l[i].length(); ++j)
                sets[i].add(l[i].charAt(j));
        }
    }
//    http://www.techiedelight.com/convert-list-string-array-string/
    public String[] findWords(String[] words) {

        List<String> list = new ArrayList<>();
        //how about empty string
        for (int i = 0;i < words.length; ++i) {
            if (words[i].length() <= 0)
                list.add(words[i]);
            else {
                int j;
                for (j = 0; j < sets.length; ++j) {
                    if (sets[j].contains(words[i].charAt(0)))
                        break;
                }

                if (j < sets.length) {
                    int k = 1;
                    while (k < words[i].length() && sets[j].contains(words[i].charAt(k))) {
                        ++k;
                    }
                    if (k >= words[i].length())
                        list.add(words[i]);
                }
            }
        }

        //go back to search
        return list.toArray(new String[0]);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String [] words = new String[]{"Hello", "Alaska", "Dad", "Peace"};
        for(String w : s.findWords(words)) {
            System.out.println(w);
        }
    }
}
