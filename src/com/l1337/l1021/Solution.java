package com.l1337.l1021;


public class Solution {

    public String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int counts = 0;
        for (int i = 0; i < S.length(); ++i)
        {
            if (S.charAt(i) == '(')
            {
                ++counts;
                if (counts > 1)
                    sb.append('(');
            }
            else
            {
                --counts;
                if (counts > 0)
                    sb.append(')');
            }
        }

        return sb.toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.removeOuterParentheses("(()())(())"));
    }
}
