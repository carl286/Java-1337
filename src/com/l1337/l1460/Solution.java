package com.l1337.l1460;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean canBeEqual(int[] target, int[] arr) {

        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < target.length; ++i)
        {
            map1.put(target[i], 1 + map1.getOrDefault(target[i], 0));
        }

        for (int i = 0; i < arr.length; ++i)
        {
            if (!map1.containsKey(arr[i]) || map1.get(arr[i]) == 0)
                return false;

            map1.put(arr[i], map1.get(arr[i]) - 1);
        }

        return true;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
