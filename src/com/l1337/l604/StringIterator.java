package com.l1337.l604;


import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/articles/desing-compressed-string-iterator/
public class StringIterator {

    private List<Character> chs;
    private List<Integer> counters;
    private int cur_indx;
    private int local_counter;

    public StringIterator(String s) {
        chs = new ArrayList<>();
        counters = new ArrayList<>();
        cur_indx = 0;
        local_counter = 0;

        int i = 0;

        while (i < s.length()) {
            if (Character.isAlphabetic(s.charAt(i))) {
                chs.add(s.charAt(i));
                ++i;
            } else {
                int acc = 0;
                while (i < s.length() && !Character.isAlphabetic(s.charAt(i))) {
                    acc = 10*acc + (s.charAt(i) - '0');
                    ++i;
                }
                counters.add(acc);
            }
        }
    }

    public char next() {
        if (hasNext()) {
            char ret = chs.get(cur_indx);
            local_counter++;
            if (local_counter >= counters.get(cur_indx))
                ++cur_indx;
            return ret;
        } else {
            return ' ';
        }
    }
    public boolean hasNext() {
        if (cur_indx >= counters.size())
            return false;
        else
            return true;
    }

    public static void main(String [] args) {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

        System.out.println(iterator.next()); // return 'L'
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.next()); // return 't'
        System.out.println(iterator.next()); // return 'C'
        System.out.println(iterator.next()); // return 'd'
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.hasNext()); // return false
        System.out.println(iterator.next()); // return ' '
    }
}
