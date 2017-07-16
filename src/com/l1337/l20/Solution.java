package com.l1337.l20;


import com.l1337.common.ListNode;

import java.util.Stack;

public class Solution {

    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)){
                case '(':
                case '{':
                case '[':
                    st.push(s.charAt(i));
                    break;
                case ')':
                    if (st.isEmpty() || st.peek() != '(')
                        return false;
                    else
                        st.pop();
                    break;
                case ']':
                    if (st.isEmpty() || st.peek() != '[')
                        return false;
                    else
                        st.pop();
                    break;
                case '}':
                    if (st.isEmpty() || st.peek() != '{')
                        return false;
                    else
                        st.pop();
                    break;
            }
        }
        return st.isEmpty();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
