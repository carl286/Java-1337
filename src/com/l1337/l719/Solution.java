package com.l1337.l719;


import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    static class Node {
        int end;
        int total;
        Node (int end, int total) {
            this.end = end;
            this.total = total;
        }
    }


//    https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109075/Java-solution-Binary-Search
//    https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-%22trial-and-error%22-algorithm
//    https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109094/Java-very-Easy-and-Short(15-lines-Binary-Search-and-Bucket-Sort)-solutions
    public int smallestDistancePair(int[] nums, int k) {
        //TLE for below code
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; ++i) {
            nums[i-1] = nums[i] - nums[i-1];
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.total - n2.total);
        for (int i = 0; i + 1 < nums.length; ++i) {
            pq.add(new Node(i, nums[i]));
        }

        int ret = -1;
        while (true) {
            Node cur = pq.poll();
            ret = cur.total;
            if (cur.end + 1 == nums.length - 1) {
                //drop;
            } else {
                cur.total += nums[++cur.end];
                pq.add(cur);
            }

            if (k == 1)
                break;
            --k;
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.smallestDistancePair(new int []{1,3,1}, 3));
    }
}
