package com.l1337.l826;


import java.util.*;

public class Solution {

    public class Node implements Comparator<Node>{
        int val;
        int index;

        public Node(int val, int index)
        {
            this.val = val;
            this.index = index;
        }
        @Override
        public int compare(Node node, Node t1) {
            if (node.val != t1.val)
                return Integer.compare(node.val, t1.val);
            return Integer.compare(node.index, t1.index);
        }
    }
    public int shortestSubarray(int[] A, int K) {
        if (A.length < 1)
            return -1;
        int ret = A.length + 1;
        if (A[0] >= K)
            return 1;
        for (int i = 1; i < A.length; ++i) {
            A[i] += A[i-1];
            if (A[i] >= K)
                ret = Math.min(ret, i + 1);
        }


        for(int i = 0; i < A.length; ++i)
        {
            //check from 0,1,2,3,....i-1,i
            for(int j = i - 1; j >= 0 && ret > i - j; --j)
            {
                if (A[i] - A[j] >= K)
                {
                    //length is i - j
                    ret = Math.min(ret, i - j);
                    break;
                }
            }
        }

        return (ret == A.length + 1) ? -1 : ret;
    }

    //Still TLS below
    public int shortestSubarrayV2(int[] A, int K) {
        if (A.length < 1)
            return -1;
        int ret = A.length + 1;
        if (A[0] >= K)
            return 1;

        TreeSet<Node> set = new TreeSet<>();
        set.add(new Node(A[0], 0));
        for(int i = 1; i < A.length; ++i)
        {
            A[i] += A[i-1];
            if (A[i] >= K)
                ret = Math.min(ret, i + 1);
            NavigableSet<Node> nav = set.headSet(new Node(A[i] - K, i), true);
            for (Node n :nav) {
                ret = Math.min(i - n.index, ret);
            }

            set.add(new Node(A[i], i));
        }

        return (ret == A.length + 1) ? -1 : ret;
    }

//    https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque
    public int shortestSubarrayV3(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            while (d.size() > 0 && B[i] - B[d.getFirst()] >=  K)
                res = Math.min(res, i - d.pollFirst());
            while (d.size() > 0 && B[i] <= B[d.getLast()])
                d.pollLast();
            d.addLast(i);
        }
        return res <= N ? res : -1;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
