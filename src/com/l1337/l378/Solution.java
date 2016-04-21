package com.l1337.l378;

//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

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

//    https://www.hrwhisper.me/leetcode-kth-smallest-element-sorted-matrix/
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
