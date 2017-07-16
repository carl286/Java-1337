package com.l1337.l648;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public String replaceWords(List<String> dict, String sentence) {

        StringBuilder sb = new StringBuilder();

        Collections.sort(dict);
        //collapse the dict
        int i = 1, last = 0;
        while (i < dict.size()) {
            if (dict.get(i).startsWith(dict.get(last))) {
                dict.remove(i);
            } else {
                last = i;
                ++i;
            }
        }

        Map<String, String> map = new HashMap<>();
        i = 0;
        while (i < sentence.length()) {
            while (i < sentence.length() && !Character.isAlphabetic(sentence.charAt(i))) {
                sb.append(sentence.charAt(i));
                ++i;
            }

            if (i < sentence.length()) {
                int j = i;
                while (j < sentence.length() && Character.isAlphabetic(sentence.charAt(j))) {
                    ++j;
                }

                String sub = sentence.substring(i, j);

                if (map.get(sub) == null) {
                    int k = 0;
                    while (k < dict.size()) {
                        if (sub.startsWith(dict.get(k)))
                            break;
                        ++k;
                    }

                    if (k < dict.size()) {
                        map.put(sub, dict.get(k));
                    }
                    else {
                        map.put(sub, sub);
                    }
                }

                sb.append(map.get(sub));
                i = j;
            }
        }

        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        List<String> st = new ArrayList<>();
        st.add("b");
        st.add("ac");
        st.add("a");
        st.add("ad");
        System.out.println(s.replaceWords(st, "a cat as acd add dd"));
    }
}
