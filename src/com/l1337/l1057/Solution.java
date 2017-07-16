package com.l1337.l1057;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {

    //        https://ttzztt.gitbooks.io/lc/content/sort/bucket-sort/campus-bikes.html
    private int dst(int [] x, int [] y)
    {
        return Math.abs(x[0]-y[0]) + Math.abs(x[1]-y[1]);
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //int [0,1,2] 0-> dist, 1 -> worker index, 2 -> bike index
        PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return a[0] - b[0];
                if (a[1] != b[1])
                    return a[1] - b[1];
                return a[2] - b[2];
            }
        });
        for (int i = 0; i < workers.length; ++i)
            for (int j = 0; j < bikes.length; ++j)
            {
                pq.add(new int [] {dst(workers[i], bikes[j]), i, j});
            }

        int [] ret = new int[workers.length];
        int count = 0;
        Set<Integer> matchedWorkers = new HashSet<>();
        Set<Integer> matchedBikes = new HashSet<>();
        while (count < ret.length)
        {
            int [] cur = pq.poll();
            if (!matchedWorkers.contains(cur[1]) && !matchedBikes.contains(cur[2]))
            {
                ret[cur[1]] = cur[2];
                ++count;
                matchedWorkers.add(cur[1]);
                matchedBikes.add(cur[2]);
            }
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int [][] workers = new int [][]{{0,0}, {2,1}};
//        int [][] bikes = new int [][]{{1,2}, {3,3}};
        int [][] workers = new int [][]{{0,0}, {1,1}, {2,0}};
        int [][] bikes = new int [][]{{1,0}, {2,2}, {2,1}};
        int ret [] = s.assignBikes(workers, bikes);
        for (int i = 0; i < ret.length; ++i)
            System.out.print(ret[i] + "\t");
    }
}
