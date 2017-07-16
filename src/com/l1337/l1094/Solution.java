package com.l1337.l1094;


import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

//    https://leetcode.com/problems/car-pooling/discuss/317610/JavaC%2B%2BPython-Meeting-Rooms-III
//    https://leetcode.com/problems/car-pooling/discuss/317887/JavaPython-3-Clean-codes-w-explanation-and-brief-analysis.
    public boolean carPooling(int[][] trips, int capacity) {
        //You don't really need pq here.... think.. about it...

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer, Integer>>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
                if (!a.getKey().equals(b.getKey()))
                    return Integer.compare(a.getKey(), b.getKey());
                else
                    return Integer.compare(a.getValue(), b.getValue());
            }
        });

        for (int i = 0; i < trips.length; ++i)
        {
            pq.add(new Pair<Integer, Integer>(trips[i][1], trips[i][0]));
            pq.add(new Pair<Integer, Integer>(trips[i][2], -trips[i][0]));
        }

        int acc = 0;
        while (!pq.isEmpty())
        {
            Pair<Integer, Integer> n = pq.poll();
            acc += n.getValue();
//            System.out.println("pos: " + n.getKey() + "\tval: " + n.getValue() + "\tcapacity: " + acc);
//            if (acc > capacity)
//                System.out.println("ACC EXCEED");
            if (acc > capacity)
                return false;
        }
        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        // System.out.println(s.carPooling(new int [][]{{2,1,5}, {3,3,7}}, 490));
        System.out.println(s.carPooling(new int [][]{{94,780,863},{40,573,889},{48,125,620},{73,150,447},{6,116,786},{72,171,548},{35,267,950},{32,169,481},{7,595,829},{65,926,982},{49,770,832},{5,232,851},{30,413,783},{5,424,454},{37,218,777},{22,143,952},{23,402,672},{65,755,847},{20,193,939},{100,586,759},{13,339,954},{40,665,767},{28,209,736},{27,11,761},{5,904,911},{56,54,332},{11,91,424},{8,379,826},{33,516,676},{66,930,960}}, 490));
    }
}
