package com.l1337.l436;


import java.util.Arrays;

import com.l1337.common.Interval;

public class Solution {
    static class Node implements Comparable<Node>{
        public int start;
        public int end;
        public int index;
        public Node(int s, int e, int i) { start = s; end = e; index=i;}

        public int compareTo(Node that) {
            return this.start - that.start;
        }
    }


//    https://leetcode.com/problems/find-right-interval/description/
//    https://leetcode.com/problems/find-right-interval/discuss/91789/Java-clear-O(n-logn)-solution-based-on-TreeMap
    //write down a easy version first, then think about it.
    public int[] findRightInterval(Interval[] intervals) {
        Node [] nodes = new Node[intervals.length];
        for (int i = 0; i < intervals.length; ++i) {
            nodes[i] = new Node(intervals[i].start, intervals[i].end, i);
        }
        Arrays.sort(nodes);

        int [] ret = new int[intervals.length];
        for (int i = 0; i < ret.length; ++i) {
            Node key = new Node(intervals[i].end, intervals[i].end, i);
            int index = Arrays.binarySearch(nodes, key);
            if (index >= 0)
                ret[i] = nodes[index].index;
            else if (-1-index < nodes.length)
                ret[i] = nodes[-1-index].index;
            else
                ret[i] = -1;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        Interval[] intervals = new Interval[]{new Interval(-100, -92)};
//        Interval[] intervals = new Interval[]{new Interval(3, 4), new Interval(2, 3), new Interval(1,2)};
        Interval[] intervals = new Interval[]{new Interval(1, 4), new Interval(2, 3), new Interval(3,4)};
        for (int i : s.findRightInterval(intervals)) {
            System.out.print(i + "\t");
        }
    }
}
