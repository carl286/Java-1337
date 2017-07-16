package com.l1337.l128;


import java.util.*;

public class Solution {

    class Range {
        int left;
        int right;

        Range(int x) {left = x; right = x;}
    }
    public int longestConsecutive(int[] nums) {
        Map<Integer, Range> map = new HashMap<>();
        int max = 0;
        for (int i : nums)
        {
            if (!map.containsKey(i))
            {
                Range latest = new Range(i);
                map.put(i, latest);
                Range left = null;
                if (map.containsKey(i-1)) {
                    left = map.get(i-1);
                    //left.left, left.right
                    latest.left = left.left;
                    left.right = latest.right;
                    map.put(left.left, left);
                }

                if (map.containsKey(i+1))
                {
                    Range right = map.get(i+1);
                    right.left = latest.left;
                    latest.right = right.right;
                    map.put(right.right, right);
                    map.put(latest.left, latest);
                }

                max = Math.max(max, latest.right - latest.left + 1);
            }
        }

        return max;
    }

    public int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = new int [] {4,1,3,2};
        System.out.println(s.longestConsecutive(nums));
    }
}
