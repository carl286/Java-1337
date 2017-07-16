package com.l1337.l99;


import com.l1337.common.TreeNode;
import javafx.util.Pair;

import java.nio.channels.Pipe;

public class Solution {
    private void swap(TreeNode a, TreeNode b)
    {
        int tmp = b.val;
        b.val = a.val;
        a.val = tmp;
    }
//    https://leetcode.com/problems/recover-binary-search-tree/submissions/
    public void recoverTree(TreeNode root) {

        TreeNode pre = null;
        Pair<TreeNode, TreeNode> firstPair = null;
        Pair<TreeNode, TreeNode> secondPair = null;

        //inorder traverse
        while (root != null)
        {
            if (root.left == null)
            {
                //visit root.val
                if (pre == null)
                {
                    pre = root;
                }
                else
                {
                    if (pre.val > root.val)
                    {
                        if (firstPair == null)
                        {
                            firstPair = new Pair<>(pre, root);
                        }
                        else
                        {
                            secondPair = new Pair<>(pre, root);
                        }
                    }

                    pre = root;
                }
                root = root.right;
            }
            else
            {
                TreeNode right;
                for (right = root.left; right.right != null && right.right != root; right = right.right);
                if (right.right == null)
                {
                    right.right = root;
                    root = root.left;
                }
                else
                {
                    right.right = null;
                    //visit root.val
                    //visit root.val
                    if (pre == null)
                    {
                        pre = root;
                    }
                    else
                    {
                        if (pre.val > root.val)
                        {
                            if (firstPair == null)
                            {
                                firstPair = new Pair<>(pre, root);
                            }
                            else
                            {
                                secondPair = new Pair<>(pre, root);
                            }
                        }

                        pre = root;
                    }
                    root = root.right;
                }
            }
        }

        if (secondPair ==  null)
        {
            swap(firstPair.getKey(), firstPair.getValue());
        }
        else
        {
            swap(firstPair.getKey(), secondPair.getValue());
        }

    }
    public static void main(String [] args) {
        Solution s = new Solution();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n3;
        n3.right = n2;

        s.recoverTree(n1);
    }
}
