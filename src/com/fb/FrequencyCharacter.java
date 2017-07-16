package com.fb;

import java.util.ArrayList;
import java.util.List;

public class FrequencyCharacter {

    /*
    given a word A and string N, find if N contains words that have the same characters and frequency of characters as A.

Eg: A = "race", N = "bacaercd", returns true because "caer" fits the criteria .

follow-up: return all indices of the occurrence.
     */
    public List<Integer> ifMatchesPattern(String A, String N)
    {
        //confirm character set???
        //assume both string are not empty...
        int [] target = new int [26];
        for(int i = 0; i < A.length(); ++i)
            ++target[A.charAt(i)-'a'];

        int [] tmp = new int [26];
        List<Integer> ret = new ArrayList<>();
        for(int i = 0, start = 0; i < N.length(); ++i)
        {
            int index = N.charAt(i)-'a';
            ++tmp[index];

            while (tmp[index] > target[index])
            {
                --tmp[N.charAt(start++)-'a'];
            }

            if (i - start + 1 == A.length())
                ret.add(start);
        }

        return ret;
    }

    public static void main(String [] args)
    {
        FrequencyCharacter f = new FrequencyCharacter();
        List<Integer> ret = f.ifMatchesPattern("race", "bacaercd");
        for(int i = 0; i < ret.size(); ++i)
            System.out.println(ret.get(i));
    }
}
