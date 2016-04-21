package com.leetcode;

import java.util.Stack;

//https://leetcode.com/problems/implement-queue-using-stacks/
public class MyQueue {
    Stack<Integer> st;
    int peek;

    public MyQueue() {
        st = new Stack<>();
    }
    // Push element x to the back of queue.
    public void push(int x) {
        st.push(x);
        if (st.size() == 1)
            peek = x;
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (empty())
            return;

        Stack<Integer> st2 = new Stack<>();
        while (!st.empty()) {
            st2.push(st.pop());
        }
        st2.pop();
        if (!st2.empty())
            peek = st2.peek();
        while (!st2.empty()) {
            st.push(st2.pop());
        }
    }

    // Get the front element.
    public int peek() {
        return peek;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return st.size() == 0;
    }
}
