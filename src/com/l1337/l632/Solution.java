package com.l1337.l632;


import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Solution {

    private boolean updateKey(List<List<Integer>> nums, TreeMap<Integer, List<int []>> map, int key) {
        //key must be existed in the map
        //getValue
        List<int []> list = map.get(key);
        //take out the first one
        int [] entry = list.remove(0);

        if (list.size() == 0)
            map.remove(key);

        ++entry[1];
        if (nums.get(entry[0]).size() == entry[1])
            return false;//reach to the end of list.

        key = nums.get(entry[0]).get(entry[1]);
        list = map.getOrDefault(key, new LinkedList<>());
        list.add(entry);
        map.put(key, list);

        return true;
    }

    public int[] smallestRange(List<List<Integer>> nums) {
//        the input list can not be empty

        TreeMap<Integer, List<int []>> map = new TreeMap<>();
        int start = Integer.MAX_VALUE;
        int rangelength = Integer.MAX_VALUE;

        for (int i = 0; i < nums.size(); ++i) {
            List<Integer> curList = nums.get(i);
            List<int []> cur = map.getOrDefault(curList.get(0), new LinkedList<>());
            cur.add(new int []{i, 0});
            map.put(curList.get(0), cur);
        }

        while (rangelength != 1) {
            int firstKey = map.firstKey();
            int lastKey = map.lastKey();

            int newRange = lastKey - firstKey + 1;
            if (newRange < rangelength || (newRange == rangelength && firstKey < start)) {
                start = firstKey;
                rangelength = newRange;
            }

            if (!updateKey(nums, map, firstKey))
                break;
        }

        return new int [] {start, start + rangelength - 1};
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
