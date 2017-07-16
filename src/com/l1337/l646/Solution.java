package com.l1337.l646;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

//    https://leetcode.com/problems/maximum-length-of-pair-chain/solution/
    public int findLongestChain(int[][] pairs) {
        if (pairs.length <= 1)
            return pairs.length;

//        List<Integer> starts = new ArrayList<>();
//        List<Integer> ends = new ArrayList<>();
////        starts.add(pairs[0][0]);
////        ends.add(pairs[0][1]);
//
//        for (int i = 0; i < pairs.length; ++i) {
//            int index = Collections.binarySearch(ends, pairs[i][1]);
//            if (index < 0) {
//                index = -(index + 1);
//                if (index == starts.size()) {
//                    starts.add(pairs[i][0]);
//                    ends.add(pairs[i][1]);
//                } else if (index == 0) {
//                    if (pairs[i][0] >= starts.get(0)) {
//                        starts.set(index, pairs[i][0]);
//                        ends.set(index, pairs[i][1]);
//                    }
//                } else {
//
//                }
//            } else {
//                //if index >= 0, it must be in bounds
//                starts.set(index, Math.min(starts.get(index), pairs[i][0]));
//            }
//        }
//
//
//
//        return ends.size();

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1])
                    return o1[1] - o2[1];
                else
                    return o2[0] - o1[0];
            }
        });

        int ret = 1;
        int [] last = pairs[0];

        for (int i = 1; i < pairs.length; ++i) {
            if (pairs[i][0] > last[1]) {
                last = pairs[i];
                ++ret;
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findLongestChain(new int [][] {{1,2}, {13,15}, {3,14}, {5,8}}));
    }
}
