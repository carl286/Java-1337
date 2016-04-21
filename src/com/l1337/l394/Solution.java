package com.l1337.l394;


import java.util.Stack;

public class Solution {

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();

        Stack<StringBuilder> st = new Stack<>();
        st.push(new StringBuilder());
        Stack<Integer> nst = new Stack<>();
        int count = 0;
//        int i = 0;
//        while (i < s.length()) {
//            //skip digits
//            int count = 0;
//            while (i < s.length() && Character.isDigit(s.charAt(i))) {
//                count = 10 * count + (s.charAt(i) - '0');
//            }
//
//            if (count == 0) {
//
//            } else {
//                nst.push()
//            }
//
//        }
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i)))
                count = 10 * count + (s.charAt(i) - '0');
            else if (count > 0) {
                nst.push(count);
                count = 0;
                //this must be '[' now
                st.push(new StringBuilder());
            } else if (s.charAt(i) != ']'){
                st.peek().append(s.charAt(i));
            } else {
                //determine whether continue to add or pop out...
                //assume this is matched for now
                StringBuilder suffix = new StringBuilder("");
//                while (!nst.isEmpty()) {
                    int times = nst.pop();
                    StringBuilder base = st.pop().append(suffix);
                    suffix = new StringBuilder(base);
                    while (--times > 0)
                        suffix = suffix.append(base);
//                }
                st.peek().append(suffix);
            }
        }
        return st.peek().toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        String test = "3[a]2[bc]";
//        String test = "3[a2[c]]";
        String test = "2[abc]3[cd]ef";
        System.out.println(s.decodeString(test));
    }
}
