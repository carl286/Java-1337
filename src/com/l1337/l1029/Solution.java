package com.l1337.l1029;


import java.util.PriorityQueue;

public class Solution {

    public int twoCitySchedCost(int[][] costs) {
        //first one value, second one index
        PriorityQueue<int []> pq = new PriorityQueue<>((a,b)->(a[0]-b[0]));
        for (int i = 0; i < costs.length; ++i)
        {
            pq.add(new int []{costs[i][0] - costs[i][1], i});
        }

        int half = costs.length >> 1;
        int ret = 0;
        while (!pq.isEmpty())
        {
            int [] cur = pq.poll();
            if (pq.size() < half)
            {
                //add b
                ret += costs[cur[1]][1];
            }
            else
            {
                ret += costs[cur[1]][0];
            }
        }
        return ret;
    }

//    https://leetcode.com/problems/two-city-scheduling/discuss/278716/C%2B%2B-O(n-log-n)-sort-by-savings
//    https://leetcode.com/problems/two-city-scheduling/discuss/278731/Java-DP-Easy-to-Understand
//    https://leetcode.com/problems/two-city-scheduling/discuss/278771/Java-sort-solution
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
