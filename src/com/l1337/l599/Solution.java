package com.l1337.l599;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; ++i)
            map.put(list1[i], i);

        Long min_sum = Long.MAX_VALUE;
        List<Integer> potentialList = new ArrayList<>();
        for (int j = 0; j < list2.length; ++j) {
            Integer i = map.get(list2[j]);
            if (i != null) {
                long localSum = (long) i + j;
                if (localSum < min_sum) {
                    min_sum = localSum;
                    potentialList.clear();
                    potentialList.add(j);
                } else if (localSum == min_sum) {
                    potentialList.add(j);
                }
            }
        }

        String [] ret = new String[potentialList.size()];
        for (int i = 0; i < ret.length; ++i)
            ret[i] = list2[potentialList.get(i)];

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
