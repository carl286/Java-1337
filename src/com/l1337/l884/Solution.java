package com.l1337.l884;


import java.util.*;

public class Solution {

    public String[] uncommonFromSentences(String A, String B) {
        String [] s1 = A.split("\\s+");
        String [] s2 = B.split("\\s+");
        Set<String> tmp = new HashSet<>();
        Set<String> bad = new HashSet<>();
        for(int i = 0; i < s1.length; ++i)
        {
            if (bad.contains(s1[i]))
                continue;
            else if (tmp.contains(s1[i]))
            {
                bad.add(s1[i]);
                tmp.remove(s1[i]);
            }
            else
            {
                tmp.add(s1[i]);
            }
        }
        for(int i = 0; i < s2.length; ++i)
        {
            if (bad.contains(s2[i]))
                continue;
            else if (tmp.contains(s2[i]))
            {
                bad.add(s2[i]);
                tmp.remove(s2[i]);
            }
            else
            {
                tmp.add(s2[i]);
            }
        }

        String ret[] = new String[tmp.size()];
        ret = tmp.toArray(ret);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
