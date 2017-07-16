package com.l1337.l525;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int findMaxLength(int[] nums) {
        int acc = 0;
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0)
                ++acc;
            else
                --acc;

            if (acc == 0)
                ret = i + 1;
            else if (map.get(acc) == null) {
                map.put(acc, i);
            } else {
                ret = Math.max(ret, i - map.get(acc));
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
