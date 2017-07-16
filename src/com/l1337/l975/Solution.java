package com.l1337.l975;


import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class Solution {

    public int oddEvenJumps(int[] A) {
//        if (A.length <= 1)
//            return A.length;
//
//        boolean [] odds = new boolean[A.length];
//        boolean [] evens = new boolean[A.length];
//        int ret = 0;
//        odds[A.length-1] = true;
//        evens[A.length-1] = true;
//
//        Stack<Integer> max = new Stack<>();
//        Stack<Integer> min = new Stack<>();
//
//        ret = 1;
//        max.push(A.length-1);
//        min.push(A.length-1);
//        for (int i = A.length-2; i >= 0; --i)
//        {
//            while (!min.isEmpty() && A[i] > A[min.peek()])
//            {
//                min.pop();
//            }
//            if (!min.isEmpty())
//            {
//                odds[i] = evens[min.peek()];
//                if (odds[i])
//                    ++ret;
//            }
//            min.push(i);
//
//            while (!max.isEmpty() && A[i] < A[max.peek()])
//            {
//                max.pop();
//            }
//            if (!max.isEmpty())
//            {
//                evens[i] = odds[max.peek()];
//            }
//            max.push(i);
//        }
//
//        return ret;
        int n  = A.length, res = 1;
        boolean[] higher = new boolean[n], lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; --i) {
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);
            if (hi != null) higher[i] = lower[(int)hi.getValue()];
            if (lo != null) lower[i] = higher[(int)lo.getValue()];
            if (higher[i]) res++;
            map.put(A[i], i);
        }
        return res;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.oddEvenJumps(new int [] {10,13,12,14,15}));
//        System.out.println(s.oddEvenJumps(new int [] {2,3,1,1,4}));
//        System.out.println(s.oddEvenJumps(new int [] {5,1,3,4,2}));
        System.out.println(s.oddEvenJumps(new int [] {1,2,3,2,1,4,4,5}));
    }
}
