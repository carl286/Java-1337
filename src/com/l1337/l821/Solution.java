package com.l1337.l821;


public class Solution {

    public int[] shortestToChar(String s, char c) {
        int [] ret = new int [s.length()];
        int lastIndex = -1;
        for(int i = 0; i < s.length(); ++i)
        {
            if (s.charAt(i) == c)
                lastIndex = i;
            ret[i] = lastIndex < 0 ? Integer.MAX_VALUE : i - lastIndex;
        }
        for(int i = s.length() - 1; i >= 0; --i)
        {
            if (s.charAt(i) == c)
                lastIndex = i;
            ret[i] = Math.min(ret[i], Math.abs(i - lastIndex));
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
