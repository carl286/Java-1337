package com.l1337.l150;


import java.util.Stack;

public class Solution {

    public int evalRPN(String[] tokens) {
        if (tokens.length <= 0)
            return 0;

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < tokens.length; ++i)
        {
            if (tokens[i].length() > 1)
                st.push(Integer.parseInt(tokens[i]));
            else {
                switch (tokens[i].charAt(0))
                {
                    case '+':
                        st.push(st.pop() + st.pop());
                        break;
                    case '-':
                        int b = st.pop();
                        int a = st.pop();
                        st.push(a - b);
                        break;
                    case '*':
                        st.push(st.pop() * st.pop());
                        break;
                    case '/':
                        b = st.pop();
                        a = st.pop();
                        st.push(a / b);
                        break;
                    default:
                        st.push(Integer.parseInt(tokens[i]));
                        break;
                }
            }
        }

        return st.peek();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
