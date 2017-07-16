package com.l1337.l856;


import java.util.Stack;

public class Solution {

//    https://leetcode.com/problems/score-of-parentheses/discuss/141777/C++JavaPython-O(1)-Space
    public int scoreOfParentheses(String S) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < S.length(); ++i)
        {
            if (S.charAt(i) == '(')
                st.push(0);
            else
            {
                int acc = 0;
                while (true)
                {
                    int top = st.pop();
                    if (top != 0)
                    {
                        acc += top;
                    }
                    else
                    {
                        if (acc == 0)
                        {
                            acc = 1;
                        }
                        else
                        {
                            acc <<= 1;
                        }
                        st.push(acc);
                        break;
                    }

                }
            }
        }

        while (st.size() > 1)
        {
            st.push(st.pop() + st.pop());
        }
        return st.peek();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.scoreOfParentheses("(()(()))"));
//        System.out.println(s.scoreOfParentheses("()"));
//        System.out.println(s.scoreOfParentheses("(())"));
        System.out.println(s.scoreOfParentheses("()()"));
    }
}
