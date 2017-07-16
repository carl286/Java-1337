package com.l1337.l68;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();

        int i  = 0;
        while (i < words.length) {
            int wordsLength = 0, j = i;
            while (j < words.length) {
                if (wordsLength + words[j].length() + (j-i) <= maxWidth) {
                    wordsLength += words[j].length();
                    ++j;
                }
                else {
                    break;
                }
            }

            int spaces = maxWidth - wordsLength;
            StringBuilder sb = new StringBuilder();
            if (j == words.length) {

                for (int l = i; l < j; ++l) {
                    sb.append(words[l]);
                    if (l != j - 1) {
                        sb.append(' ');
                    }
                }
                while (sb.length() < maxWidth)
                    sb.append(' ');
            } else {
                //i, i+1, i+2, ...j-1
                int cnt = j - i;
                int div, mod;
                if (cnt == 1) {
                    div = 0;
                    mod = 0;
                } else {
                    div = spaces / (j - i - 1);
                    mod = spaces % (j - i - 1);
                }

                for (int l = i; l < j; ++l) {
                    sb.append(words[l]);
                    if (l != j - 1) {
                        char [] tmpArray = new char [div + (mod == 0 ? 0 : 1)];
                        Arrays.fill(tmpArray, ' ');
                        if (mod > 0)
                            --mod;
                        sb.append(new String(tmpArray));
                    } else {
                        while (sb.length() < maxWidth) {
                            sb.append(' ');
                        }
                    }
                }
            }
            ret.add(sb.toString());
            i = j;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String [] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 20;
        List<String> ret = s.fullJustify(words, maxWidth);
        for (int i = 0; i < ret.size(); ++i) {
            System.out.println(ret.get(i));
        }
    }
}
