package com.l1337.l901;


import java.util.Stack;

public class StockSpanner {

//    https://leetcode.com/problems/online-stock-span/solution/
    /*
    Think about how to improve it....
     */
//    https://leetcode.com/problems/online-stock-span/discuss/168311/C%2B%2BJavaPython-O(1)
//    https://github.com/xiaoylu/leetcode_category/tree/master/MonotonicQueue.
    private Stack<Integer> st = new Stack<>();
    public StockSpanner() {

    }

    public int next(int price) {
        st.push(price);
        Stack<Integer> cur = new Stack<>();
        int ret = 0;
        while (!st.isEmpty() && st.peek() <= price)
        {
            ++ret;
            cur.push(st.pop());
        }

        while (!cur.isEmpty())
        {
            st.push(cur.pop());
        }

        return ret;

        /*
        Stack<Integer> cur = (Stack<Integer>) st.clone();
        int ret = 0;
        while (!cur.isEmpty() && cur.peek() <= price)
        {
            ++ret;
            cur.pop();
        }


        return ret;
        */

    }

    public static void main(String [] args) {
        StockSpanner s = new StockSpanner();
        System.out.println("Hello World");
    }
}
