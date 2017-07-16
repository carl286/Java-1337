package com.l1337.l394;


import java.util.Stack;

public class Solution {

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> st = new Stack<>();
        Stack<StringBuilder> sbt = new Stack<>();
        int i = 0;
        StringBuilder tmp = new StringBuilder();
        int number = 0;
        while(i < s.length()) {
            if (Character.isLetter(s.charAt(i))) {
                if (sbt.isEmpty())
                    sb.append(s.charAt(i));
                else
                    sbt.peek().append(s.charAt(i));
            } else if (Character.isDigit(s.charAt(i))) {
                number = 10 * number + (s.charAt(i) - '0');
            } else if (s.charAt(i) == '[') {
                st.push(number);
                sbt.push(new StringBuilder());
                number = 0;
            } else { // should be ']' here
                int top = st.pop();
                StringBuilder tmp3 = sbt.pop();
                StringBuilder tmp2 = new StringBuilder();
                while (top > 0) {
                    tmp2.append(tmp3);
                    --top;
                }
                if (sbt.isEmpty()) {
                    sb.append(tmp2);
                } else {
                    sbt.peek().append(tmp2);
                }
            }

            ++i;
        }
        return sb.toString();


        //the code can be more cleaner if you put a empty builder in advance
//        StringBuilder sb = new StringBuilder();
//
//        Stack<StringBuilder> st = new Stack<>();
//        st.push(new StringBuilder());
//        Stack<Integer> nst = new Stack<>();
//        int count = 0;
//        for (int i = 0; i < s.length(); ++i) {
//            if (Character.isDigit(s.charAt(i)))
//                count = 10 * count + (s.charAt(i) - '0');
//            else if (count > 0) {
//                nst.push(count);
//                count = 0;
//                //this must be '[' now
//                st.push(new StringBuilder());
//            } else if (s.charAt(i) != ']'){
//                st.peek().append(s.charAt(i));
//            } else {
//                //determine whether continue to add or pop out...
//                //assume this is matched for now
//                StringBuilder suffix = new StringBuilder("");
////                while (!nst.isEmpty()) {
//                int times = nst.pop();
//                StringBuilder base = st.pop().append(suffix);
//                suffix = new StringBuilder(base);
//                while (--times > 0)
//                    suffix = suffix.append(base);
////                }
//                st.peek().append(suffix);
//            }
//        }
//        return st.peek().toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String tmps = "2[abc]3[cd]ef";
        System.out.println(s.decodeString(tmps));
    }
}
