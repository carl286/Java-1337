package com.l1337.l548;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Solution {

    /*
    Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:
0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.

Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5.
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1

Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].

     */

    public boolean splitArray(int[] nums) {
        if (nums.length < 7)
            return false;

        Map<Integer, HashSet<Integer>> preMap = new HashMap<>();
        Map<Integer, HashSet<Integer>> postMap = new HashMap<>();

        int acc = 0;
        for (int i = 0; i < nums.length; ++i) {
            acc += nums[i];
            HashSet<Integer> cur = preMap.getOrDefault(acc ,new HashSet<>());
            cur.add(i);
            if (cur.size() == 1)
                preMap.put(acc, cur);
        }

        acc = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            acc += nums[i];
            HashSet<Integer> cur = postMap.getOrDefault(acc ,new HashSet<>());
            cur.add(i);
            if (cur.size() == 1)
                postMap.put(acc, cur);
        }

        for (Map.Entry<Integer, HashSet<Integer>> pre : preMap.entrySet()) {
            int pre_sum = pre.getKey();
            HashSet<Integer> pre_indexes = pre.getValue();

            HashSet<Integer> post_indexes = postMap.get(pre_sum);

            if (post_indexes != null) {
                //merge indexes
                Iterator<Integer> pre_iterator = pre_indexes.iterator();
                while (pre_iterator.hasNext()) {
                    Iterator<Integer> post_iterator = post_indexes.iterator();
                    int i = pre_iterator.next();
                    while (post_iterator.hasNext()) {
                        int j = post_iterator.next();
                        if (j - i >= 5) {
                            ++i;
                            --j;
                            int ask_pre_sum = (pre_sum << 1) + nums[i];
                            int ask_post_sum = (pre_sum << 1) + nums[j];
                            HashSet<Integer> pre_indexes2 = preMap.get(ask_pre_sum);
                            if (pre_indexes2 != null) {

                                Iterator<Integer> pre_iterator2 = pre_indexes2.iterator();
                                while (pre_iterator2.hasNext()) {
                                    int k = pre_iterator2.next();
                                    ++k;
                                    if (k - i >= 2 && j - k >= 2) {
                                        if (postMap.getOrDefault(ask_post_sum, new HashSet<>()).contains(k + 1))
                                            return true;
                                    }
                                }
                            }

                            postMap.get(ask_post_sum);
                        }
                    }
                }
            }
        }
        return false;
    }
//    https://blog.csdn.net/magicbean2/article/details/78867872
//    https://www.jianshu.com/p/b766486b3096

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.splitArray(new int [] {1,2,1,2,1,2,1}));
    }
}
