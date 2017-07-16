package com.l1337.l1209;


import java.util.Stack;

public class Solution {

    class Node{
        char c;
        int count;
        Node(char c, int count)
        {
            this.c = c;
            this.count = count;
        }
    }
//    https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/discuss/392933/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution
    public String removeDuplicates(String s, int k) {
        Stack<Node> st = new Stack<>();
        for (int i = 0; i < s.length(); ++i)
        {
            if (st.isEmpty() || st.peek().c != s.charAt(i))
            {
                st.push(new Node(s.charAt(i), 1));
            }
            else
            {
                ++st.peek().count;
            }

            if (!st.isEmpty() && st.peek().count == k)
                st.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
        {
            Node n = st.pop();
            for(int i = 0; i < n.count; ++i)
            {
                sb.append(n.c);
            }
        }
        return new String(sb.reverse());
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
