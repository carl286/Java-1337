package com.l1337.l560;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

//    https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-+-HashMap
//https://leetcode.com/problems/subarray-sum-equals-k/discuss/134689/Three-Approaches-With-Explanation
    public int subarraySum(int[] nums, int k) {
        //BF
//        if (nums.length <= 0)
//            return 0;
//
//        int ret = 0;
//        if (nums[0] == k)
//            ++ret;
//
//        for (int i = 1; i < nums.length; ++i) {
//            nums[i] += nums[i-1];
//            if (nums[i] == k)
//                ++ret;
//        }
//
//        for (int i = 0; i + 1 < nums.length; ++i) {
//            for (int j = i + 1; j < nums.length; ++j)
//                if (nums[j] - nums[i] == k)
//                    ++ret;
//        }
//
//        return ret;

        //using map
        //        if (nums.length <= 0)
//            return 0;
//
//        int ret = 0;
//        if (nums[0] == k)
//            ++ret;
//
//        for (int i = 1; i < nums.length; ++i) {
//            nums[i] += nums[i-1];
//            if (nums[i] == k)
//                ++ret;
//        }

        if (nums.length <= 0)
            return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int acc = 0;
        for (int i = 0; i < nums.length; ++i) {
            acc += nums[i];
            List<Integer> cur = map.getOrDefault(acc, new ArrayList<>());
            cur.add(i);
            map.put(acc, cur);
        }

        int ret = 0;
        List<Integer> cur = map.getOrDefault(k, null);
        if (cur != null)
            ret += cur.size();

        //how to remove this loop....
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> l1 = entry.getValue();

            int key2 = key + k;
            List<Integer> l2 = map.getOrDefault(key2, null);

            if (l2 != null) {
                //merge l1 and l2
                int length1 = l1.size(), length2 = l2.size();
                int i = 0, j = 0;
                while (i < length1 && j < length2) {
                    if (l1.get(i) >= l2.get(j)) {
                        ++j;
                    } else {
                        ret += length2 - j;
                        ++i;
                    }
                }
            }
        }
        return ret;
    }

























    public int subarraySumMarch8_21(int[] nums, int k) {
        if (nums.length <= 0)
            return 0;
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; ++i)
        {
            if (i != 0)
            {
                nums[i] += nums[i-1];
            }

            if (nums[i] == k)
                ++ret;
            ret += map.getOrDefault(nums[i] - k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.subarraySum(new int[]{1}, 0));
    }
}
