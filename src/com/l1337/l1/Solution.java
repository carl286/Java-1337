package com.l1337.l1;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int [] ret = new int [2];

        // from a value to its index
        Map<Integer, Integer> valueMap = new HashMap<>();

        for (int i = 0; i < nums.length; ++i)
        {
            int tmp = target - nums[i];
            if (valueMap.containsKey(tmp) && (long)nums[valueMap.get(tmp)] + nums[i] == target)
            {
                ret[0] = valueMap.get(tmp);
                ret[1] = i;
                break;
            }

            valueMap.put(nums[i], i);
        }

        return ret;

    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int nums [] = {2,2147483646, -1, Integer.MIN_VALUE + 1};
        int target = Integer.MIN_VALUE;
        int ret [] = s.twoSum(nums, target);
        System.out.println(Integer.MIN_VALUE - 2);
        System.out.println(ret[0] + "\t" + ret[1]);
    }
}
