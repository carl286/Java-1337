package com.l1337.l144;


import com.l1337.common.TreeNode;

import java.util.*;

public class Solution {
//
//    https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45582/A-real-Postorder-Traversal-.without-reverse-or-insert-4ms
//    https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45803/Java-solution-using-two-stacks
    // https://leetcode.com/problems/binary-tree-preorder-traversal/discuss/45466/C%2B%2B-Iterative-Recursive-and-Morris-Traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty())
        {
            TreeNode cur = st.pop();
            ret.add(cur.val);
            if (cur.right != null)
                st.push(cur.right);
            if (cur.left != null)
                st.push(cur.left);
        }

        return ret;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (!st.isEmpty())
        {
            TreeNode cur = st.pop();
            ret.add(cur.val);
            if (cur.left != null)
                st.push(cur.left);
            if (cur.right != null)
                st.push(cur.right);

        }

        Collections.reverse(ret);
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
