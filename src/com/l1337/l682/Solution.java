package com.l1337.l682;


import java.util.Stack;

public class Solution {

    public int calPoints(String[] ops) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < ops.length; ++i) {
            if (ops[i].equals("+")) {
                if (st.size() < 2)
                    return -1;

                int a = st.pop();
                int b = a + st.peek();
                st.push(a);
                st.push(b);
            } else if (ops[i].equals("D")) {
                if (st.isEmpty())
                    return -1;
                else
                    st.push(st.peek() << 1);
            } else if (ops[i].equals("C")) {
                if (st.isEmpty())
                    return -1;
                else
                    st.pop();
            } else {
                st.push(Integer.parseInt(ops[i]));
            }
        }

        int ret = 0;
        while (!st.isEmpty()) {
            ret += st.pop();
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.calPoints(new String [] {"5","2","C","D","+"}));
        System.out.println(s.calPoints(new String [] {"5","-2","4","C","D","9","+","+"}));
    }
}
