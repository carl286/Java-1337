package com.l1337.l732;


import java.util.Map;
import java.util.TreeMap;

//https://leetcode.com/problems/my-calendar-iii/discuss/109568/Java-Solution-O(n-log(len))-beats-100-Segment-Tree
public class MyCalendarThree {

    TreeMap<Integer, Integer> map;

    public MyCalendarThree() {
        map = new TreeMap<>();
    }

    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int ret = 0;
        int acc = 0;
        boolean rollback = false;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            acc += entry.getValue();
            ret = Math.max(ret, acc);
        }

        return ret;
    }

    public static void main(String [] args) {
        MyCalendarThree s = new MyCalendarThree();
        System.out.println("Hello World");
    }
}
