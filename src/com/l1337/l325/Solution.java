package com.l1337.l325;

import java.util.HashMap;

//Maximum Size Subarray Sum Equals k
//Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//	Example 1:
//	Given nums = [1, -1, 5, -2, 3], k = 3,
//			return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
//	Example 2:
//	Given nums = [-2, -1, 2, 1], k = 1,
//			return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
//	Follow Up:
//	Can you do it in O(n) time?
public class Solution {

    public int maxSubArrayLen(int[] nums, int k) {
        int ret = 0;
        if (nums.length > 0) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(nums[0], 0);
            if (nums[0] == k)
                ret = 1;
            for (int i = 1; i < nums.length; ++i) {

                //This is not needed
//                if (nums[i] == k) {
//                    ret = Math.max(1, ret);
//                }
                nums[i] += nums[i-1];
                if (nums[i] == k)
                    ret = (i+1);
                if (map.containsKey(nums[i] - k)) {
                    ret = Math.max(i - map.get(nums[i] - k), ret);
                }
                if (!map.containsKey(nums[i]))
                    map.put(nums[i], i);
            }
        }

        return ret;

        /*
        //You can merge them even more....
		int sum = 0, max = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			if (sum == k) max = i + 1;
			else if (map.containsKey(sum - k))
				max = Math.max(max, i - map.get(sum - k));
			if (!map.containsKey(sum)) map.put(sum, i);
		}
		return max;
         */


        /*
        //Or use this trick...
        public class Logger {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // IMPOARTANT   <------- See it's trick....
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }

            if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }
        }

        return maxLen;
    }
}
         */
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        /*
        int [] nums = {1, -1, 5, -2, 3};
        int k = 3;
        */
        int [] nums = {-2, -1, 2, 1};
        int k = 1;
        System.out.println(s.maxSubArrayLen(nums,k));
    }
}
