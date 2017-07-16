package com.l1337.l844;


import java.util.Stack;

public class Solution {

    private Stack<Character> getStack(String s)
    {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); ++i)
        {
            if (s.charAt(i) == '#')
            {
                if (!st.isEmpty())
                    st.pop();
            }
            else
                st.push(s.charAt(i));
        }

        return st;
    }
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s1 = getStack(S), s2 = getStack(T);
        if (s1.size() != s2.size())
            return false;
        while (!s1.isEmpty())
        {
            if (s1.pop() != s2.pop())
                return false;
        }

        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
