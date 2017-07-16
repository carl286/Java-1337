package com.l1337.l559;


import java.util.LinkedList;
import java.util.List;

public class Solution {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        if (root == null)
            return 0;

        int depth = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ++depth;
            LinkedList<Node> next_queue = new LinkedList<>();
            //assume no nulls in the list
            while (!queue.isEmpty()) {
                Node cur = queue.removeFirst();
                if (cur.children != null)
                    next_queue.addAll(cur.children);
            }

            queue = next_queue;
        }


        return depth;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
