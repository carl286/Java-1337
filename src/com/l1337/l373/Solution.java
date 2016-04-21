package com.l1337.l373;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ret = new ArrayList<>();
        if (k == 0)
            return ret;
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node l, Node r) {
                return Integer.compare(l.val, r.val);
            }
        });
        pq.add(new Node(nums1[0]+nums2[0], 0, 0));
        while (ret.size() < k && !pq.isEmpty()) {
            Node top = pq.poll();
            int [] tmp = new int[]{nums1[top.i], nums2[top.j]};
            ret.add(tmp);
            if (top.j + 1 < nums2.length) {
                pq.add(new Node(nums1[top.i]+nums2[top.j+1], top.i, top.j+1));
            }
            if (top.i + 1 < nums1.length && top.j == 0) {
                pq.add(new Node(nums1[top.i+1]+nums2[top.j], top.i+1, top.j));
            }
        }
        return ret;
    }


//    http://bookshadow.com/weblog/2016/07/07/leetcode-find-k-pairs-with-smallest-sums/

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int [] nums1 = {1,1,2};
        int [] nums2 = {1,2,3};
        int k = 10;
        for (int [] iter : s.kSmallestPairs(nums1, nums2, k)) {
            System.out.println((iter[0] + iter[1]) + "\t" + iter[0] + "\t" +  iter[1]);
        }
    }
}
