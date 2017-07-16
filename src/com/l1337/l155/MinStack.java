package com.l1337.l155;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStack {

    Stack<Integer> st;
    List<Integer> list;
    /** initialize your data structure here. */
    public MinStack() {
        st = new Stack<>();
        list = new ArrayList<>();
    }

    public void push(int x) {
        st.push(x);
        if (list.isEmpty())
            list.add(x);
        else
            list.add(list.get(list.size()-1) < x ? list.get(list.size()-1) : x);
    }

    public void pop() {
        st.pop();
        list.remove(list.size()-1);
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return list.get(list.size()-1);
    }

//    https://leetcode.com/submissions/detail/62503441/
    public static void main(String [] args) {
        MinStack s = new MinStack();
        System.out.println("Hello World");
    }
}
