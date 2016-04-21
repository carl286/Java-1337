package com.leetcode;

import java.util.Stack;

//https://leetcode.com/problems/min-stack/
//Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//        push(x) -- Push element x onto stack.
//        pop() -- Removes the element on top of the stack.
//        top() -- Get the top element.
//        getMin() -- Retrieve the minimum element in the stack.
public class MinStack {

    Stack<Integer> st;
    Stack<Integer> mins;
    /** initialize your data structure here. */
    public MinStack() {
        st = new Stack<>();
        mins = new Stack<>();
    }

    public void push(int x) {
        st.push(x);
        if (mins.isEmpty() || mins.peek() >= x)
            mins.push(x);
    }

    public void pop() {
        int val = st.pop();
        if (val == mins.peek())
            mins.pop();
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return mins.peek();
    }

    public static void main(String [] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());//   --> Returns -3.
        minStack.pop();
        System.out.println(minStack.top());//      --> Returns 0.
        System.out.println(minStack.getMin());//   --> Returns -2.
    }
}

