package com.l1337.l282;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//283. Move Zeroes
//https://leetcode.com/problems/move-zeroes/
public class Solution {

    String [] ops = {"+", "-", "*"};
    Long evaluate(Long a, Long b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0l;
    }

    long evaluateString(String s) {
        //assume input string is not empty
        Stack<Long> st = new Stack<>();
        Stack<Character> op = new Stack<>();
        int i = 0;
        while (i < s.length()) {
                Long sum = 0l;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    sum = 10*sum + (s.charAt(i)-'0');
                    ++i;
                }
                st.push(sum);
                if (!op.isEmpty() && op.peek() == '*') {
                    op.pop();
                    long b = st.pop();
                    long a = st.pop();
                    st.push(a*b);
                }
                if (i < s.length()) {
                    //op
                    if ((s.charAt(i) == '+' || s.charAt(i) == '-') && !op.isEmpty()) {
                        long b = st.pop();
                        long a = st.pop();
                        st.push(evaluate(a,b,op.pop()));
                    }
                    op.push(s.charAt(i));
                }

            ++i;
        }

        if (!op.isEmpty()) {
            long b = st.pop();
            long a = st.pop();
            st.push(evaluate(a,b,op.pop()));
        }
        return st.peek();
    }

    //Don't consider overflow here...
    void addOperatorsHelper(String num, String sb, int index, int target, List<String> ret) {
        if (index >= num.length()) {
            if (evaluateString(sb) == target)
                ret.add(sb);
            return;
        }

        for (int i = index; i < num.length(); ++i) {
            //index won't be 0 here.
            if (num.charAt(index) == '0' && i > index)
                break;

            for (int k = 0; k < ops.length; ++k) {
                addOperatorsHelper(num, sb + ops[k] + num.substring(index, i + 1), i + 1, target, ret);
            }
        }
    }
    public List<String> addOperators(String num, int target) {
        List<String> ret = new ArrayList<>();

        for (int i = 0; i < num.length(); ++i) {
            if (num.charAt(0) == '0' && i > 0)
                break;
            addOperatorsHelper(num, num.substring(0, i + 1), i + 1, target, ret);
        }
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        String num = "123";
//        int targer = 6;
//        String num = "232";
//        int targer = 8;
//        String num = "105";
//        int targer = 5;
//        String num = "00";
//        int targer = 0;

//        String num = "3456237490";
//        int targer = 9191;
        String num = "2147483648";
        int targer = -2147483648;
        for (String ss : s.addOperators(num, targer)) {
            System.out.println(ss);
        }
    }
}

//FYI
//https://leetcode.com/submissions/detail/59906494/
