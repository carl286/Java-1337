package com.l1337.l791;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/custom-sort-string/discuss/152006/Java-2ms-no-sorting
    static class Customer implements Comparator<Character> {

        private Map<Character, Integer> _map;
        public Customer(Map<Character, Integer> map)
        {
            this._map = map;
        }
        //Who is positive, who will go out first...
        public int compare(Character one, Character two) {
//            return two - one;
//            return Integer.compare(one, two);
            return Integer.compare(this._map.getOrDefault(one, 100), this._map.getOrDefault(two, 100));
        }
    }

    public String customSortStringV2(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < S.length(); ++i)
            map.put(S.charAt(i), i);
        char [] a1 = T.toCharArray();
        Character [] data = new Character[a1.length];// = T.toCharArray();
        for(int i = 0; i < a1.length; ++i)
            data[i] = a1[i];
        //Arrays.sort(data);
        Arrays.sort(data, new Customer(map));

        for(int i = 0; i < a1.length; ++i)
            a1[i] =  data[i];

        return new String(a1);
    }

    public String customSortString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < S.length(); ++i)
            map.put(S.charAt(i), i);
        char [] a1 = T.toCharArray();
        Character [] data = new Character[a1.length];// = T.toCharArray();
        for(int i = 0; i < a1.length; ++i)
            data[i] = a1[i];
        //Arrays.sort(data);
        int [] data2 = Arrays.stream(data).
                sorted((a, b) -> map.getOrDefault(a, 100).compareTo(map.getOrDefault(b, 100))). // sort descending
                mapToInt(i -> i).
                toArray();
        for(int i = 0; i < a1.length; ++i)
            a1[i] = (char) data2[i];

        return new String(a1);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String S = "cba", T = "abcd";
        System.out.println(s.customSortString(S,T));
    }
}
