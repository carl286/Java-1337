package com.l1337.l554;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxReaches = 0;
        for (int i = 0; i < wall.size(); ++i) {
            List<Integer> list = wall.get(i);
            int acc = 0;
            for (int j = 0; j + 1 < list.size(); ++j) {
                acc += list.get(j);
                int cache = map.getOrDefault(acc, 0);
                map.put(acc, ++cache);
                if (cache > maxReaches)
                    maxReaches = cache;
            }
        }

        return wall.size() - maxReaches;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
