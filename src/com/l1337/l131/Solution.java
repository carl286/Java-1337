package com.l1337.l131;


import java.util.*;

public class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        if (s.length() <= 0)
            return ret;

        boolean [][] map = new boolean[s.length()][s.length()];

        for (int i = map.length - 1; i >= 0; i--)
        {
            map[i][i] = true;
            for (int j = i + 1; j < map.length; ++j)
            {
                map[i][j] = (s.charAt(i) == s.charAt(j)) && ((j - i < 2) ? true : map[i+1][j-1]);
            }
        }

        List<List<List<Integer>>> another = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i)
            another.add(new ArrayList<>());

        for (int i = s.length() - 1; i >= 0; --i)
        {
            for (int j = i; j < s.length() - 1; ++j) {
                if (map[i][j])
                {
                    //cross product, j, j + 1
                    //ending j, fetching j+1
                    //cache might be empty
                    List<List<Integer>> cache = another.get(j+1);
                    for (int k = 0; k < cache.size(); ++k)
                    {
                        List<Integer> tmp = new ArrayList<>(cache.get(k));
                        tmp.add(j);
                        another.get(i).add(tmp);
                    }
                }
            }
            //fill the list
            if (map[i][s.length()-1])
            {
                another.get(i).add(new ArrayList<>(List.of(s.length()-1)));
            }
        }

        //restore from 0
        for (int i = 0; i < another.get(0).size(); ++i)
        {
            List<String> tmp = new ArrayList<>();
            int start = 0;
            for (int j = another.get(0).get(i).size() - 1; j >= 0; --j)
            {
                tmp.add(s.substring(start, another.get(0).get(i).get(j) + 1));
                start = another.get(0).get(i).get(j) + 1;
            }
            ret.add(tmp);
        }

        return ret;
    }

    public int minCut(String s) {
        if (s.length() <= 1)
            return 0;
        boolean [][] map = new boolean[s.length()][s.length()];

        for (int i = map.length - 1; i >= 0; i--)
        {
            map[i][i] = true;
            for (int j = i + 1; j < map.length; ++j)
            {
                map[i][j] = (s.charAt(i) == s.charAt(j)) && ((j - i < 2) ? true : map[i+1][j-1]);
            }
        }

        int [] tmp = new int[s.length()];
        tmp[s.length() - 1] = 0;

        for (int i = s.length() - 1; i >= 0; --i)
        {
            if (map[i][s.length() - 1])
            {
                tmp[i] = 0;
            }
            else
            {
                tmp[i] = s.length() - 1 - i;
                for (int j = i; j < s.length() -1; ++j)
                {
                    if (map[i][j])
                        tmp[i] = Math.min(tmp[i], 1 + tmp[j + 1]);
                }
            }

        }
        return tmp[0];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        List<List<String>> ret = s.partition("aaaa");

        for (List<String> ans: ret)
        {
            for (String ss : ans)
            {
                System.out.print(ss + "\t");
            }
            System.out.println();
        }
    }
}
