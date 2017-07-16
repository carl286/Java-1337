package com.l1337.l1439;


import java.util.*;

public class Solution {

    class Node implements Comparable<Node>
    {
        int [] indexes;
        int val;

        @Override
        public boolean equals(Object obj) {
            Node that = (Node) obj;
//            if (obj == null)
//                return false;
//            if (this.indexes.length != that.indexes.length)
//                return false;
            for (int i = 0; i < this.indexes.length; ++i)
                if (this.indexes[i] != that.indexes[i])
                    return false;
            return true;
        }

        @Override
        public int hashCode() {
            return this.val;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.val, node.val);
        }
    }
    public int kthSmallest(int[][] mat, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Node> expanded = new HashSet<>();

        //init
        Node n = new Node();
        n.indexes = new int [mat.length];
        for (int i = 0; i < n.indexes.length; ++i)
            n.val += mat[i][n.indexes[i]];

        pq.add(n);
        expanded.add(n);

        int ret = 0;
        while (k > 0)
        {
            Node cur = pq.poll();

            ret = cur.val;
            --k;
            if (k <= 0)
                break;

            //expands
            for (int i = 0; i < cur.indexes.length; ++i)
            {
                if (cur.indexes[i] + 1 < mat[0].length)
                {
                    Node newNode = new Node();
                    newNode.val = cur.val - mat[i][cur.indexes[i]] + mat[i][cur.indexes[i] + 1];
                    newNode.indexes = new int[mat.length];
                    System.arraycopy(cur.indexes, 0, newNode.indexes, 0, mat.length);
                    ++newNode.indexes[i];
                    if (!expanded.contains(newNode))
                    {
                        pq.add(newNode);
                        expanded.add(newNode);
                    }
                }
            }

        }

        return ret;
    }

//    https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/discuss/609775/C%2B%2B-Search-from-beginning-or-from-top
//    https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/discuss/667582/Java-using-idea-of-%22dp%22
public int kthSmallestv2(int[][] mat, int k) {
    int m = mat.length, n = mat[0].length, res = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    pq.offer(0);
    for (int i = 0; i < m; i++) {
        PriorityQueue<Integer> nq = new PriorityQueue<>(Collections.reverseOrder());
        for (int sum : pq){
            for (int j = 0; j < n; j++) {
                nq.offer(sum + mat[i][j]);
                if (nq.size() > k) nq.poll();
            }
        }
        pq = nq;
    }
    return pq.poll();
}

//    https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/discuss/610701
//    https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/discuss/609851/Two-solutions%3A-Dijkstraand-Python-generator
    public static void main(String [] args) {
        Solution s = new Solution();
        //System.out.println(s.kthSmallest(new int [][]{{1,3,11}, {2,4,6}}, 9));
        System.out.println(s.kthSmallest(new int [][]{{1,1,10}, {2,2,9}}, 7));
    }
}
