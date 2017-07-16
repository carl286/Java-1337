package com.l1337.l1326;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public int minTaps(int n, int[] ranges) {
        PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int []>() {
            public int compare(int [] a, int []b)
            {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                else
                    return Integer.compare(a[1], b[1]);
            }
        });

        for(int i = 0; i < ranges.length; ++i)
        {
            pq.add(new int [] {i - ranges[i], i + ranges[i]});
        }

        int lastCovered = 0;
        int steps = 0;
        while (!pq.isEmpty() && lastCovered < n)
        {
            int maxNext = -1;
            while (!pq.isEmpty())
            {
                if (pq.peek()[1] < lastCovered)
                {
                    pq.poll();
                }
                else if (pq.peek()[0] <= lastCovered)
                {
                    maxNext = Math.max(maxNext, pq.peek()[1]);
                    pq.poll();
                }
                else
                {
                    break;
                }
            }

            if (maxNext >= 0)
            {
                ++steps;
                lastCovered = maxNext;
                //skip
//                while (!pq.isEmpty() && pq.peek()[1] < lastCovered)
//                {
//                    pq.poll();
//                }
            }
            else
            {
                break;
            }
        }
        return lastCovered < n ? -1 : steps;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int n = 9;
        int [] ranges = new int [] {0,5,0,3,3,3,1,4,0,4};

        System.out.println(s.minTaps(n, ranges));
    }
}
