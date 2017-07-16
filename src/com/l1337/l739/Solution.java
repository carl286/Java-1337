package com.l1337.l739;


import java.util.*;

public class Solution {
//    https://leetcode.com/problems/daily-temperatures/discuss/109832/Java-Easy-AC-Solution-with-Stack
    public int[] dailyTemperatures(int[] T) {
        int[] ret = new int [T.length];

        //0 val, 1 index
        PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < T.length; ++i) {
            while (!pq.isEmpty() && pq.peek()[0] < T[i]) {
                int [] top = pq.poll();
                ret[top[1]] = i - top[1];
            }
            pq.add(new int []{T[i], i});
        }

        return ret;
    }



    public int[] dailyTemperaturesFeb11_21(int[] T) {
        int[] ret = new int[T.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < T.length; ++i)
        {
            while (!st.isEmpty() && T[st.peek()] < T[i] )
            {
                int pre = st.pop();
                ret[pre] = i - pre;
            }
            st.push(i);
        }


        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
