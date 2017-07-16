package com.l1337.l729;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyCalendar {


//    https://leetcode.com/problems/my-calendar-ii/discuss/109522/Simplified-winner's-solution
//    https://leetcode.com/problems/my-calendar-ii/discuss/109519/JavaC++-Clean-Code-with-Explanation
//    https://leetcode.com/problems/my-calendar-ii/discuss/112761/JavaClean-Code-with-TreeMap-+-List-Beat-75
    TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int acc = 0;
        boolean rollback = false;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            acc += entry.getValue();
            if (acc >= 3) {
                rollback = true;
                break;
            }
        }

        if (rollback) {
            map.put(start, map.get(start) - 1);
            map.put(end, map.get(end) + 1);
            return false;
        } else  {
            return true;
        }
    }

    public static void main(String [] args) {
        MyCalendar myCalendar= new MyCalendar();
        System.out.println(myCalendar.book(10, 20)); // returns true
        System.out.println(myCalendar.book(15, 25)); // returns false
        System.out.println(myCalendar.book(20, 30)); // returns true
    }
}
