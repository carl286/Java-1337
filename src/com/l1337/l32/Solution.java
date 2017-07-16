package com.l1337.l32;


import java.util.*;

public class Solution {

    public int longestValidParentheses(String s) {
        int ret = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                st.push(-1);
            } else {
                if (!st.isEmpty()) {
                    int peek = st.peek();
                    if (peek == 0) {
                        //nothing to do here
                    } else {
                        // peek > 0 || peek < 0
                        if (peek == -1) {
                            st.pop();
                            int local = 2;
                            while (!st.isEmpty() && st.peek() > 0) {
                                local += st.pop();
                            }
                            st.push(local);
                            ret = Math.max(local, ret);
                        } else {
                            //peek > 0, need check if previous one < 0
                            int top = st.pop();
                            if (!st.isEmpty() && st.peek() == -1) {
                                st.pop();
                                int local = top + 2;
                                while (!st.isEmpty() && st.peek() > 0) {
                                    local += st.pop();
                                }
                                st.push(local);
                                ret = Math.max(local, ret);
                            } else {
                                st.push(0);
                            }
                        }

                    }
                }
            }
        }


        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
// String ss = new String(")()())");
        String ss = new String("()(())");
        System.out.println(s.longestValidParentheses(ss));
    }
}
