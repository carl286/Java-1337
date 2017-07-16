package com.l1337.l503;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {

//    https://leetcode.com/problems/next-greater-element-ii/description/

//    https://leetcode.com/problems/next-greater-element-ii/discuss/165636/C++-short-easy-to-understand-std::stack-solution
//    https://leetcode.com/problems/next-greater-element-ii/discuss/98273/Java-10-lines-and-C++-12-lines-linear-time-complexity-O(n)-with-explanation
    public int[] nextGreaterElements(int[] nums) {

        //val, index
        TreeMap<Integer, List<Integer>> tree = new TreeMap<>((a, b) -> (a.compareTo(b)));
        Set<Integer> unfinished = new HashSet<>();
        int[] ret = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {
//            int [] newVal = new int [] {nums[i], i};
            Map.Entry<Integer, List<Integer>> lower;
            while ((lower = tree.lowerEntry(nums[i])) != null) {
                tree.remove(lower.getKey());
                for (Integer index : lower.getValue()) {
                    ret[index] = nums[i];
                    unfinished.remove(index);
                }
            }

            List<Integer> cur = tree.getOrDefault(nums[i], new ArrayList<>());
            cur.add(i);
            tree.put(nums[i], cur);
            unfinished.add(i);
        }

        //val, index
        PriorityQueue<int []> smallPq = new PriorityQueue<>((a,b) -> (a[0] - b[0]));
        for (Integer i : unfinished)
            smallPq.add(new int [] {nums[i], i});

        TreeSet<int []> tree2 = new TreeSet<>((a,b) -> (a[0] - b[0]));

        for (int i = 0; i < nums.length && !smallPq.isEmpty(); ++i) {
            tree2.add(new int []{nums[i], i});
            int [] last;
            while (!smallPq.isEmpty() && (last = tree2.last())[0] > smallPq.peek()[0]) {
                int [] top = smallPq.poll();
                if (top[1] > i) {
                    ret[top[1]] = last[0];
                } else {
                    ret[top[1]] = -1;
                }
            }
        }

        while (!smallPq.isEmpty()) {
            int [] top = smallPq.poll();
            ret[top[1]] = -1;
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int[] nums = new int [] {1,2,3,4};
//        int[] nums = new int [] {4,3,2,1};
//        int[] nums = new int [] {1,2,3,2,1};
        int[] nums = new int [] {2,2,3};
        for (int i : s.nextGreaterElements(nums)) {
            System.out.print(i + "\t");
        }
    }
}
