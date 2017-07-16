package com.l1337.l94;


import com.l1337.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();

        while (root != null)
        {
            if (root.left == null)
            {
                ret.add(root.val);
                root = root.right;
            }
            else
            {
                TreeNode left;
                for (left = root.left; left.right != null && left.right != root; left = left.right);

                if (left.right == null)
                {
                   left.right = root;
                   root = root.left;
                }
                else
                {
                    left.right = null;
                    ret.add(root.val);
                    root = root.right;
                }
            }
        }

        return ret;
    }

    public List<Integer> inorderTraversalApril9_21(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();

        while (!st.isEmpty() || root != null)
        {
            if (root != null)
            {
                while (root.left != null)
                {
                    st.push(root);
                    root = root.left;
                }
                ret.add(root.val);
                root = root.right;
            }
            else
            {
                root = st.pop();
                ret.add(root.val);
                root = root.right;
            }
        }
        return ret;
    }
//    http://web.archive.org/web/20150308080843/http://noalgo.info/832.html
    public static void main(String [] args) {

        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
