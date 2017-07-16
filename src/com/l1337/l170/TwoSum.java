package com.l1337.l170;

import java.util.HashMap;
import java.util.Map;

/*
Design and implement a TwoSum class. It should support the following operations: add and find.
add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.
Example 1:
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:
add(3); add(1); add(2);
find(3) -> true
find(6) -> false
 */

//https://medium.com/@ukpnair/170-two-sum-iii-data-structure-design-790f660a02da
//https://aaronice.gitbook.io/lintcode/high_frequency/two-sum-iii-data-structure-design
public class TwoSum {
    private Map<Integer, Integer> map = new HashMap<>();
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry: map.entrySet())
        {
            Integer v2 = map.get(value - entry.getKey());
            if (v2 != null && ((value - entry.getKey() != entry.getKey()) || (v2 > 1)))
                return true;
        }

        return false;
    }
    public static void main(String [] args) {
        TwoSum s = new TwoSum();
        s.add(1);
        s.add(3);
        s.add(5);
        System.out.println(s.find(4));
        System.out.println(s.find(7));
    }
}
