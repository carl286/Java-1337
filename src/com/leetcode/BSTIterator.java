package com.leetcode;

import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//https://leetcode.com/problems/binary-search-tree-iterator/
//http://yucoding.blogspot.com/2015/03/leetcode-question-binary-search-tree.html
//http://blog.csdn.net/whuwangyi/article/details/42304407
public class BSTIterator {
    Stack<TreeNode> st = new Stack<>();

    public BSTIterator(TreeNode root) {
        if (root != null)
            st.push(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !st.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if (!st.isEmpty()) {
            TreeNode top = st.pop();
            //Whenever a node's left is null, it means it has already been visited or null
            if (top.left == null) {
                int ret = top.val;
                if (top.right != null) {
                    st.push(top.right);
                }
                return ret;
            } else {
                TreeNode left = top.left;
                top.left = null;
                st.push(top);
                while (left.left != null) {
                    TreeNode next = left.left;
                    left.left = null;
                    st.push(left);
                    left = next;
                }
                int ret = left.val;
                if (left.right != null)
                    st.push(left.right);
                return ret;
            }
        }
        return 0;
    }
}
