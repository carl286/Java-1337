package com.l1337.l893;


import java.util.HashSet;
import java.util.Set;

public class Solution {

    private String getHash(String s)
    {
        int odds [] = new int [26];
        int evens [] = new int [26];
        for(int i = 0; i < s.length(); ++i)
        {
            if ((i & 0x01) == 0)
            {
                ++odds[s.charAt(i)-'a'];
            }
            else
            {
                ++evens[s.charAt(i)-'a'];
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < evens.length; ++i)
        {
            if (evens[i] != 0)
            {
                sb.append(evens[i]);
                sb.append((char)('a' + i));
            }
        }
        sb.append('#');
        for(int i = 0; i < odds.length; ++i)
        {
            if (odds[i] != 0)
            {
                sb.append(odds[i]);
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }
    public int numSpecialEquivGroups(String[] A) {
        Set<String> tmp = new HashSet<>();
        for(int i = 0; i < A.length; ++i)
            tmp.add(getHash(A[i]));

        return tmp.size();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.numSpecialEquivGroups(new String[]{"abcd","cdab","cbad","xyzz","zzxy","zzyx"}));
    }
}
