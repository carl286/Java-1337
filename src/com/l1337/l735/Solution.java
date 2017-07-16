package com.l1337.l735;


import java.util.Stack;

public class Solution {
    private boolean isConflict(int a, int b) {
        return (a > 0 && b < 0);
    }

    private int absorb(int a, int b) {
        //we know we have to absorb here
        int c = a + b;
        if (c == 0)
            return 0;
        else if (c > 0)
            return a > b ? a : b;
        else
            return a < b ? a : b;
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < asteroids.length; ++i) {
            if (st.isEmpty()) {
                st.push(asteroids[i]);
            } else if (isConflict(st.peek(), asteroids[i])) {
                int toAdd = asteroids[i];
                while (!st.isEmpty() && isConflict(st.peek(), toAdd)) {
                    toAdd = absorb(st.pop(), asteroids[i]);
                    if (toAdd == 0) {
                        break;
                    }
                }

                if (toAdd != 0) {
                    st.push(toAdd);
                }
            } else {
                st.push(asteroids[i]);
            }
        }

        int [] ret = new int [st.size()];
        int i = ret.length;
        while (!st.isEmpty()) {
            ret[--i] = st.pop();
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
