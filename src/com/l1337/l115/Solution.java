package com.l1337.l115;


public class Solution {

    public int numDistinct(String s, String t) {
        int [][] data = new int [t.length()+1][s.length()+1];

        for (int i = 0; i < data[0].length; ++i)
            data[0][i] = 1;

        for (int i = 1; i < data.length; ++i)
        {
            for (int j = 1; j < data[i].length; ++j)
            {
                //fill
                data[i][j] = (s.charAt(j-1) == t.charAt(i-1) ? data[i-1][j-1]: 0) + data[i][j-1];
            }
        }

        return data[t.length()][s.length()];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");

        String S = "babgbag", T = "bag";
        System.out.println(s.numDistinct(S,T));
    }
}
