package com.leetcode;

//Zigzag Iterator
//    Given two 1d vectors, implement an iterator to return their elements alternately.
//    For example, given two 1d vectors:
//    v1 = [1, 2]
//    v2 = [3, 4, 5, 6]
//    By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
//    Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

import java.util.*;

public class ZigzagIterator {
    List<Iterator> list;
    int index;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<>();
        if (v1 != null) {
            Iterator<Integer> i1 = v1.iterator();
            if (i1.hasNext())
                list.add(i1);
        }
        if (v2 != null) {
            Iterator<Integer> i2 = v2.iterator();
            if (i2.hasNext())
                list.add(i2);
        }
        if (list.size() > 0)
            index = 0;
        else
            index = -1;
    }
    public int next() {
        //must have one value.
        int ret = (int) list.get(index).next();
        if (list.get(index).hasNext()) {
            index = (index + 1) % list.size();
        } else {
            list.remove(index);
            if (list.size() == 0)
                index = -1;
            else if (index >= list.size())
                index = 0;
        }
        return ret;
    }
    public boolean hasNext() {
        return index != -1;
    }

    public static void main(String [] args) {
        List<Integer> v1 = Arrays.asList(new Integer [] {1,2,3,4});
        List<Integer> v2 = Arrays.asList(new Integer [] {0});
        ZigzagIterator z = new ZigzagIterator(v1, v2);
        while (z.hasNext()) {
            System.out.print(z.next() + "\t");
        }
        System.out.println();
    }
}
