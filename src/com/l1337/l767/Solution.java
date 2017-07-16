package com.l1337.l767;


import java.util.Arrays;
import java.util.Comparator;

public class Solution {

//    https://leetcode.com/problems/reorganize-string/discuss/232469/Java-No-Sort-O(N)-0ms-beat-100
    public String reorganizeString(String s) {
        if (s.length() <= 1)
            return s;
        Character [] data = s.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        int [] counts = new int [26];
        int max = 0;
        for(int i = 0; i < data.length; ++i) {
            ++counts[data[i]-'a'];
            max = Math.max(max, counts[data[i]-'a']);
        }
        if (max > (data.length + 1)/2)
        {
            return "";
        }
        Arrays.sort(data, new Comparator<>(){
            public int compare(Character a, Character b)
            {
                 if (counts[b-'a'] != counts[a-'a'])
                    return counts[b-'a']-counts[a-'a'];
                 else
                     return b - a;
            }
        });
        char [] tmp = new char [data.length];
        int k = 0;
        for(int i = 0; i < tmp.length; i += 2)
            tmp[i] = data[k++];
        for(int i = 1; i < tmp.length; i += 2)
            tmp[i] = data[k++];
        return new String(tmp);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.reorganizeString("abbabbaaab"));
    }
}
