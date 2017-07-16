package com.l1337.l921;


public class Solution {

    public int minAddToMakeValid(String s) {
        int diff = 0;
        int ret = 0;
        for(int i = 0; i < s.length(); ++i)
        {
            if (s.charAt(i) == '(')
            {
                ++diff;
            }
            else if (diff == 0)
            {
                ++ret;
            }
            else
                --diff;
        }

        ret += diff;
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
