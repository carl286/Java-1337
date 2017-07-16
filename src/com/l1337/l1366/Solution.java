package com.l1337.l1366;


import javafx.util.Pair;

import java.util.*;

public class Solution {

    public String rankTeams(String[] votes) {
        int length = votes[0].length();
        Map<Character, int []> map = new HashMap<>();
        for(int i = 0; i < votes.length; ++i)
        {
            for(int j = 0; j < votes[i].length(); ++j)
            {
                map.putIfAbsent(votes[i].charAt(j), new int [length]);
                ++map.get(votes[i].charAt(j))[j];
            }
        }
        List<Pair<Character, int []>> list = new ArrayList<>();
        for(Map.Entry<Character, int []> entry : map.entrySet())
        {
            list.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        Collections.sort(list, new Comparator<Pair<Character, int []>>() {
            public int compare(Pair<Character, int []> a, Pair<Character, int []>b)
            {
                int [] arrayA = a.getValue(), arrayB = b.getValue();
                for(int i =0; i < arrayA.length; ++i)
                {
                    if (arrayA[i] != arrayB[i])
                        return Integer.compare(arrayB[i], arrayA[i]);
                }
                return a.getKey().compareTo(b.getKey());
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); ++i)
            sb.append(list.get(i).getKey());
        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
