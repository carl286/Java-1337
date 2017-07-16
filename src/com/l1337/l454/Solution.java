package com.l1337.l454;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private Map<Integer, Integer> convert(int [] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; ++i) {
            int oldVal = map.getOrDefault(a[i], 0);
            map.put(a[i], oldVal + 1);
        }
        return map;
    }
//    http://www.cnblogs.com/grandyang/p/6073317.html
    //You are so close....
    //Time OUT
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        Map<Integer, Integer> mapA = convert(A), mapB = convert(B), mapC = convert(C), mapD = convert(D);
        int ret = 0;
        for (Map.Entry<Integer, Integer> entryA : mapA.entrySet()) {
            for (Map.Entry<Integer, Integer> entryB : mapB.entrySet()) {
                for (Map.Entry<Integer, Integer> entryC : mapC.entrySet()) {
                    int sum = entryA.getKey() + entryB.getKey() + entryC.getKey();
                    int val = mapD.getOrDefault(-sum, 0);
                    if (val != 0)
                        ret += val * entryA.getValue() * entryB.getValue() * entryC.getValue();
                }
            }
        }

        return ret;
    }

    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {

        int ret = 0;
        Map<Integer, Integer> mapA = new HashMap<>();
        for (int i = 0; i < A.length; ++i)
            for (int j = 0; j < B.length; ++j) {
                int sum = A[i] + B[j];
                int old = mapA.getOrDefault(sum, 0);
                mapA.put(sum, old + 1);
            }

        for (int i = 0; i < C.length; ++i)
            for (int j = 0; j < D.length; ++j) {
                int sum = C[i] + D[j];
                int old = mapA.getOrDefault(-sum, 0);
                ret += old;
            }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
