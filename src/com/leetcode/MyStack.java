package com.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Ke.Liu on 5/19/16.
 */
public class MyStack {
    Queue<Integer> q;
    Queue<Integer> back_q;
    Integer last;
    MyStack() {
        q = new ArrayDeque<>();
        back_q = new ArrayDeque<>();
        last = null;
    }
    // Push element x onto stack.
    public void push(int x) {
        q.add(x);
        last = x;
    }

    // Removes the element on top of the stack.
    public void pop() {
        while (q.size() != 1) {
            last = q.remove();
            back_q.add(last);
        }
        q.remove();
        if (back_q.size() == 0)
            last = null;
        else {
            Queue<Integer> tmp = q;
            q = back_q;
            back_q = tmp;
        }

    }

    // Get the top element.
    public int top() {
        return last;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return last == null;
    }
}
