package com.l1337.l1249;


import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solution {

    public String minRemoveToMakeValid(String s) {
        Set<Integer> set = new HashSet<>();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); ++i)
        {
            switch (s.charAt(i))
            {
                case '(':
                    st.push(i);
                    break;
                case ')':
                    if (!st.isEmpty())
                    {
                        set.add(st.pop());
                        set.add(i);
                    }
                    break;
                default:
                    set.add(i);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i)
        {
            if (set.contains(i))
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }

















    public String minRemoveToMakeValidMarch10_21(String s) {
        Set<Integer> removed = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); ++i)
        {
            if (s.charAt(i) == '(')
            {
                st.push(i);
            }
            else if (s.charAt(i) == ')')
            {
                if (st.isEmpty())
                {
                    removed.add(i);
                }
                else
                {
                    st.pop();
                }
            }
        }

        while (!st.isEmpty())
        {
            removed.add(st.pop());
        }

        for(int i = 0; i < s.length(); ++i)
        {
            if (!removed.contains(i))
            {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.minRemoveToMakeValidMarch10_21("())()"));
    }
}
