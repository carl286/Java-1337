package com.l1337.l1047;


import java.util.Stack;

public class Solution {

//    https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/discuss/294893/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution
    public String removeDuplicates(String S) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < S.length(); ++i)
        {
            if (st.isEmpty() || st.peek() != S.charAt(i))
                st.push(S.charAt(i));
            else
                st.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
        {
            sb.append(st.pop());
        }
        return new String(sb.reverse());
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
