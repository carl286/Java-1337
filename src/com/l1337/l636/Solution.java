package com.l1337.l636;


import java.util.List;
import java.util.Stack;

public class Solution {
    static class Node {
        int startTime;
        int totalTimeUsedByOthers;
    }

//    https://leetcode.com/problems/exclusive-time-of-functions/discuss/105062/Java-Stack-Solution-O(n)-Time-O(n)-Space
    public int[] exclusiveTime(int n, List<String> logs) {
        int [] ret = new int[n];
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < logs.size(); ++i) {
            String cur = logs.get(i);
            String splits [] = cur.split(":");
            if (splits[1].equals("end")) {
                //pop stack
                Node top = st.pop();
                int fid = Integer.parseInt(splits[0]);
                int endTime = Integer.parseInt(splits[2]);
                ret[fid] += endTime - top.startTime + 1 - top.totalTimeUsedByOthers;
                if (!st.isEmpty()) {
                    st.peek().totalTimeUsedByOthers += (endTime - top.startTime + 1);
                }
            } else {
                st.push(new Node());
                st.peek().startTime = Integer.parseInt(splits[2]);
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
