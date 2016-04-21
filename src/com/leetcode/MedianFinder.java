package com.leetcode;


import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * Created by Ke.Liu on 4/29/16.
 */
//https://leetcode.com/problems/find-median-from-data-stream/
//https://leetcode.com/discuss/64910/very-short-o-log-n-o-1
public class MedianFinder {
    // Adds a number into the data structure.
    /* Please read the problem...
    Stack<Integer> s1;
    Deque<Integer> s2;
    MedianFinder() {
        s1 = new Stack<>();
        s2 = new ArrayDeque<>();
    }
    public void addNum(int num) {
        //s1.size() >= s2.size();
        if (s1.size() == s2.size()) {
            if (s2.isEmpty()) {
                s1.push(num);
                return;
            } else {
                s1.push(s2.removeFirst());
            }
        }
        s2.add(num);
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (s1.size() == 0)
            return 0;
        else if (s1.size() != s2.size())
            return s1.peek();
        else
            return (s1.peek() + s2.getFirst()) / 2.0;

    }

    */

    /* FUCK, input is not ordered
    List<Integer> list;
    MedianFinder() {
        list = new LinkedList<>();
    }
    public void addNum(int num) {
        int size = list.size();
        if (size == 0) {
            list.add(num);
        } else if ((size & 0x01) == 0) {
            list.remove(0);
            list.add(num);
        } else {
            //Odd
            list.add(num);
        }
    }
    public double findMedian() {
        if ((list.size() & 0x01) == 0) {
            return (list.get(0) + list.get(1)) / 2.0;
        } else {
            return list.get(0);
        }
    }
    */

    static class ComparSort implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return Integer.compare(b,a);
        }
    }
    PriorityQueue<Integer> pq1;
    PriorityQueue<Integer> pq2;
    MedianFinder() {
        //pq1, large value go out first
        pq1 = new PriorityQueue<>(new ComparSort());
        //pq2, small value go out first
        pq2 = new PriorityQueue<>();
    }
    public void addNum(int num) {
        if (pq1.isEmpty()) {
            pq1.add(num);
            return;
        }

//        if (pq2.isEmpty()) {
//            pq1.add(num);
//            pq2.add(pq1.poll());
//            return;
//        }

        //Both are not empty. May not be true here..
        if (num > pq1.peek())
            pq2.add(num);
        else
            pq1.add(num);

        if (pq1.size() - pq2.size() > 1)
            pq2.add(pq1.poll());
        else if (pq2.size() - pq1.size() > 1)
            pq1.add(pq2.poll());
    }
    public double findMedian() {
        if (pq1.size() != pq2.size())
            return pq1.size() > pq2.size() ? pq1.peek() : pq2.peek();
        else
            return (pq2.peek() + pq1.peek()) / 2.0;
//  Using larger integer types also prevents an overflow error when taking the mean of the two middle numbers. I think almost all solutions posted previously have that bug.
//        https://leetcode.com/discuss/64850/short-simple-java-c-python-o-log-n-o-1
    }

    public static void main(String [] args) {
        MedianFinder mf = new MedianFinder();
        /*
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(10);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(5); System.out.println(mf.findMedian()); mf.addNum(0); System.out.println(mf.findMedian()); mf.addNum(6); System.out.println(mf.findMedian()); mf.addNum(3); System.out.println(mf.findMedian()); mf.addNum(1); System.out.println(mf.findMedian()); mf.addNum(0); System.out.println(mf.findMedian()); mf.addNum(0); System.out.println(mf.findMedian());
        */
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }
}
