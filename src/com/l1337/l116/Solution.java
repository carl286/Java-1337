package com.l1337.l116;
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class Solution {


    public Node connect(Node root) {
        Node ret = root;

        while (root != null)
        {
            Node next = null;
            Node pre = new Node(0);
            //pre.next = next;

            while (root != null)
            {
                if (root.left != null)
                {
                    if (next == null) next = root.left;
                    pre.next = root.left;
                    //root.left.next = root.right;
                    pre = root.left;
                }

                if (root.right != null)
                {
                    if (next == null) next = root.right;
                    pre.next = root.right;
                    pre = root.right;
                }
                root = root.next;
            }


            root = next;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
