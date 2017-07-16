package com.l1337.l71;

import java.util.Stack;

public class Solution {

    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();

        String [] paths = path.split("/");

        for (int i = 0; i < paths.length; ++i) {
           // System.out.println(paths[i].length());
            if (paths[i].length() == 0 || paths[i].equals(".")) {
                continue;
            } else if (paths[i].equals("..")) {
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else {
                st.push(paths[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.insert(0, st.pop());
            sb.insert(0, '/');
        }
        if (sb.length() == 0)
            sb.insert(0, '/');

        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String path = "/home////";
        System.out.println(s.simplifyPath(path));
    }
}
