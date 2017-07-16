package com.l1337.l589;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

//    https://leetcode.com/problems/n-ary-tree-preorder-traversal/discuss/147955/Java-Iterative-and-Recursive-Solutions
    public List<Integer> preorder(Node root) {
        List<Integer> ret = new ArrayList<>();
        Deque<Node> queue = new LinkedList<>();
        if (root != null)
            queue.addFirst(root);

        while (!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            ret.add(cur.val);

            if (cur.children != null) {
//                for (int i = cur.children.size() - 1; i >= 0 ; --i)
//assume all node are not null
                for (int i = cur.children.size() - 1; i >= 0 ; --i)
                    queue.addFirst(cur.children.get(i));
            }
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
