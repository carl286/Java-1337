package com.leetcode;

//Two Sum III - Data structure design
//        Design and implement a TwoSum class. It should support the following operations: add and find.
//        add - Add the number to an internal data structure.
//        find - Find if there exists any pair of numbers which sum is equal to the value.
//
//        For example,
//        add(1); add(3); add(5);
//        find(4) -> true
//        find(7) -> false


import java.util.HashMap;

//Are they support duplicate??
//if no duplciate, hashset, else hashmap
public class TwoSum {
    HashMap<Integer, Integer> map;
    public TwoSum() {
        map = new HashMap<Integer, Integer>();
    }

    public void add(int x) {
        if (map.containsKey(x)) {
            map.put(x, map.get(x)+1);
        }
        else {
            map.put(x, 1);
        }
    }

    public boolean find(int target) {
        for (int i : map.keySet()) {
//            关键点在于对重复数字的处理，用hashmap存储值，然后判断value-num == num的情况下，count是否>=2.
            if (map.containsKey(target-i)) {
                if (target - i != i) return true;
                else if (map.get(i) >= 2) return true;
            }
        }
        return false;
    }
}
