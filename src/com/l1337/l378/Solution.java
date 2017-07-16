package com.l1337.l378;

//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
    int val;
    int i;
    int j;
    Node(int val, int i, int j) {
        this.val = val;
        this.i = i;
        this.j = j;
    }
}

public class Solution {

//    You may assume k is always valid, 1 ≤ k ≤ n2.
    public int kthSmallestI(int[][] matrix, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node l, Node r) {
                return Integer.compare(l.val, r.val);
            }
        });

        int ret = matrix[0][0];
        pq.add(new Node(matrix[0][0], 0, 0));

        while (k > 0) {
            Node top = pq.poll();
            ret = top.val;
            --k;
            if (top.j + 1 < matrix[0].length) {
                pq.add(new Node(matrix[top.i][top.j+1], top.i, top.j+1));
            }
            if (top.i + 1 < matrix.length && top.j == 0) {
                pq.add(new Node(matrix[top.i+1][top.j], top.i+1, top.j));
            }
        }

        return ret;
    }

    public int countSmallest(int [][] matrix, int val)
    {
        int cnt = 0;

        return cnt;
    }


    public int kthSmallest2Helper(int[][] matrix, int val) {
        int total = 0;
        for (int i = 0; i < matrix.length; ++i)
        {
            int k = Arrays.binarySearch(matrix[i], val);
            if (k >= 0)
            {
                //issues with duplicates..

                //total += k;
                total += k;
                while (k < matrix[i].length && matrix[i][k] == val)
                {
                    total++;
                    ++k;
                }
            }
            else
            {
                total += -(k + 1);
            }
        }
        return total;
    }

    public int kthSmallest2(int[][] matrix, int k) {
        //it's guaranteed that 1 <= k <= n^2
        int startR = matrix.length - 1, startC = 0;
        int closestK = Integer.MAX_VALUE;
        int ret = matrix[startR][startC];

        while (startR >= 0 && startC < matrix.length)
        {
            int count = kthSmallest2Helper(matrix, matrix[startR][startC]);
//            if (count == k)
//            {
//                return matrix[startR][startC];
//            }
            if (count == k)
                return matrix[startR][startC];
            if (count < k)
            {
                ++startC;
            }
            else
            {   // count > k
                if (closestK > count)
                {
                    ret = matrix[startR][startC];
                    closestK = count;
                }
                --startR;

            }
        }
        return ret;
    }

//    https://www.hrwhisper.me/leetcode-kth-smallest-element-sorted-matrix/
    public static void main(String [] args) {
        Solution s = new Solution();
        //int [][] matrix = new int[][]{{1,5,9}, {10,11,13}, {12,13,15}};
        int [][] matrix = new int[][]{{1,3,5}, {6,7,12}, {11,14,14}};
        int k = 8;
//
//        int [][] matrix = new int[][]{{1,1,1}, {1,1,1}, {11,14,14}};
//        for (int c = 1; c <= 9; ++c)
        System.out.println(s.kthSmallest2(matrix, k));
    }
}
