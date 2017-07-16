package com.l1337.l1650;


import com.l1337.common.TreeNode;

import java.util.Stack;

public class Solution {

//    https://leetcode.ca/2020-06-06-1650-Lowest-Common-Ancestor-of-a-Binary-Tree-III/
//    https://zhenchaogan.gitbook.io/leetcode-solution/leetcode-1650-lowest-common-ancestor-of-a-binary-tree-iii

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Stack<Node> stp = new Stack<>();
        Stack<Node> stq = new Stack<>();
        while (p != null)
        {
            stp.push(p);
            p = p.parent;
        }
        while (q != null)
        {
            stq.push(q);
            q = q.parent;
        }

        Node ret = stp.peek();
        while (!stp.isEmpty() && !stq.isEmpty() && stp.peek() == stq.peek())
        {
            ret = stp.pop();
            stq.pop();
        }
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
